package Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Models.Student;
public class addStudent extends AppCompatActivity {
    private TextInputEditText etStudentName, etPhoneNumber, etRFID, etPassword, etYear, etFeeAmount;
    private Button btnSubmit;
    private AutoCompleteTextView subjectsDropdown;
    private List<String> selectedSubjects = new ArrayList<>();


    private ActivityResultLauncher<String[]> filePickerLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initializeViews();
        setupSubjectsDropdown();
        setupValidations();
    }

    private void setupSubjectsDropdown() {
        // CALL TO DB TODO
        List<String> subjects = Arrays.asList(
                "Introduction to Programming",
                "Data Structures",
                "Algorithms",
                "Database Management",
                "Web Development",
                "Computer Networks",
                "Operating Systems"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, subjects);
        subjectsDropdown.setAdapter(adapter);

        subjectsDropdown.setOnItemClickListener((parent, view, position, id) -> {
            String subject = parent.getItemAtPosition(position).toString();
            subjectsDropdown.setHint("Select subjects (click again to remove)");
            if (selectedSubjects.contains(subject)) {
                selectedSubjects.remove(subject);
            } else {
                selectedSubjects.add(subject);
            }
            subjectsDropdown.setText("");
            updateSubjectsDisplay();
        });
    }

    //TODO overlap of error and show password

    private void updateSubjectsDisplay() {
        StringBuilder display = new StringBuilder();
        for (String subject : selectedSubjects) {
            if (display.length() > 0) {
                display.append(", ");
            }
            display.append(subject);
        }
        subjectsDropdown.setHint(display.toString());
    }

    private void initializeViews() {
        etStudentName = findViewById(R.id.etStudentName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etRFID = findViewById(R.id.etRFID);
        etPassword = findViewById(R.id.etPassword);
        etYear = findViewById(R.id.etYear);
        etFeeAmount = findViewById(R.id.etFeeAmount);
        subjectsDropdown = findViewById(R.id.subjectsDropdown);

        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupValidations() {
        //NAME VALIDATIONS
        etStudentName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 2) {
                    etStudentName.setError("Name must be at least 2 characters long");
                } else if (!s.toString().matches("^[a-zA-Z\\s]*$")) {
                    etStudentName.setError("Name should only contain letters");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //PHONE # VALIDATIONS
        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().matches("^[0-9]{10}$")) {
                    etPhoneNumber.setError("Enter a valid 10-digit phone number");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        //PASSWORD VALIDATIONS
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6) {
                    etPassword.setError("Password must be at least 6 characters long");
                } else if (!s.toString().matches(".*[A-Z].*")) {
                    etPassword.setError("Password must contain at least one uppercase letter");
                } else if (!s.toString().matches(".*[0-9].*")) {
                    etPassword.setError("Password must contain at least one number");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //YEARVALIDATIONS
        etYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    int year = Integer.parseInt(s.toString());
                    if (year < 1 || year > 4) {
                        etYear.setError("Year must be between 1 and 4");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnSubmit.setOnClickListener(v -> validateAndSubmit());
    }

    private void validateAndSubmit() {
        if (!validateInputs()) {
            return;
        }

        Student student = new Student.StudentBuilder(
                etStudentName.getText().toString(),
                etPhoneNumber.getText().toString(),
                etPassword.getText().toString())
                .rfid(Integer.parseInt(etRFID.getText().toString()))
                .year(Integer.parseInt(etYear.getText().toString()))
                .campusId(getCampusId())
                .studentId(generateStudentID())
                .absenteeId(generateAbsenteeID())
                .feeAmount(Integer.parseInt(etFeeAmount.getText().toString()))
                .subjects(selectedSubjects)
                .build();

        saveStudent(student);
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (TextUtils.isEmpty(etStudentName.getText())) {
            etStudentName.setError("Name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etPhoneNumber.getText()) ||
                !etPhoneNumber.getText().toString().matches("^[0-9]{10}$")) {
            etPhoneNumber.setError("Valid phone number is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etRFID.getText())) {
            etRFID.setError("RFID is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etPassword.getText()) ||
                etPassword.getText().length() < 6) {
            etPassword.setError("Valid password is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etYear.getText()) ||
                !isValidYear(etYear.getText().toString())) {
            etYear.setError("Valid year is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etFeeAmount.getText())) {
            etFeeAmount.setError("Fee amount is required");
            isValid = false;
        }

        if (selectedSubjects.isEmpty()) {
            subjectsDropdown.setError("At least one subject is required");
            isValid = false;
        }
        return isValid;
    }



    private boolean isValidYear(String yearStr) {
        try {
            int year = Integer.parseInt(yearStr);
            return year >= 1 && year <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void saveStudent(Student student) {
        //loading indi
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding student...");
        progressDialog.show();

        // DATABASE CALL TODO
//        saveStudentToDatabase(student, new DatabaseCallback() {
//            @Override
//            public void onSuccess() {
//                progressDialog.dismiss();
//                showSuccessDialog();
//            }
//
//            @Override
//            public void onError(String error) {
//                progressDialog.dismiss();
//                showErrorDialog(error);
//            }
//        });
    }


    private int generateStudentID() {
        // DB CALL TODO
        return 0;
    }

    private String generateAbsenteeID() {
        //  DB CALL TODO
        return "";
    }

    private int getCampusId() {
        // DB CALL TODO
        return 0;
    }
}

//TODO CSV LOGIC:
