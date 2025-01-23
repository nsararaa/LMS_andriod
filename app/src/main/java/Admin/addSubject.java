package Admin;

import android.app.TimePickerDialog;
import android.net.DnsResolver;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Models.Campus;
import Models.Teacher;

import Models.Subject;

public class addSubject extends AppCompatActivity {
    private TextInputEditText etSubjectName, etTime, etYear;
    private AutoCompleteTextView dayDropdown, teacherDropdown, campusDropdown;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        initializeViews();
        setupDropdowns();
        setupTimePicker();
        setupSubmitButton();
    }

    private void initializeViews() {
        etSubjectName = findViewById(R.id.etSubjectName);
        etTime = findViewById(R.id.etTime);
        etYear = findViewById(R.id.etYear);
        dayDropdown = findViewById(R.id.dayDropdown);
        teacherDropdown = findViewById(R.id.teacherDropdown);
        campusDropdown = findViewById(R.id.campusDropdown);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    private void setupDropdowns() {
        // Setup days dropdown
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<>(
                this, R.layout.dropdown_item, days
        );
        dayDropdown.setAdapter(daysAdapter);

        // Setup teachers dropdown (assuming you have a Teacher model and repository)
        loadTeachers();

        // Setup campus dropdown
        loadCampuses();
    }

    private void setupTimePicker() {
        etTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    this,
                    (view, hourOfDay, minute) -> {
                        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        etTime.setText(time);
                    },
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    true
            );
            timePickerDialog.show();
        });
    }

    private void loadTeachers() {
        // db call DB


    }

    private void loadCampuses() {
        // Similar implementation to loadTeachers() for campus data
    }
    private Calendar getTimeFromInput(String timeInput) {
        String[] parts = timeInput.split(":"); // Assumes input is "HH:mm"
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0); // Optional: Reset seconds
        calendar.set(Calendar.MILLISECOND, 0); // Optional: Reset milliseconds

        return calendar;
    }

    private void setupSubmitButton() {
        btnSubmit.setOnClickListener(v -> {
            if (validateInputs()) {
                Subject subject = new Subject(
                        etSubjectName.getText().toString(),
                        dayDropdown.getText().toString(),
                        getTimeFromInput(etTime.getText().toString()),
                        ((Teacher) teacherDropdown.getTag()).getId(),
                        ((Campus) campusDropdown.getTag()).getCampusID(),
                        Integer.parseInt(etYear.getText().toString()),
                        ((Teacher) teacherDropdown.getTag()).getName()
                );

                saveSubject(subject);
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (TextUtils.isEmpty(etSubjectName.getText())) {
            etSubjectName.setError("Subject name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(dayDropdown.getText())) {
            dayDropdown.setError("Day is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etTime.getText())) {
            etTime.setError("Time is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(teacherDropdown.getText())) {
            teacherDropdown.setError("Teacher is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(campusDropdown.getText())) {
            campusDropdown.setError("Campus is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(etYear.getText())) {
            etYear.setError("Year is required");
            isValid = false;
        }

        return isValid;
    }

    private void saveSubject(Subject subject) {
        // TODO save logic
    }

}