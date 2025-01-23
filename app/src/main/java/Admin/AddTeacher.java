package Admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.Arrays;

public class AddTeacher extends AppCompatActivity {
    private TextInputEditText etTeacherName, etPassword;
    private AutoCompleteTextView subjectDropdown, campusDropdown;
    private Button btnSubmit, btnBulkSubmit;
    private MaterialCardView bulkUploadCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        initializeViews();
        setupDropdowns();
        setupClickListeners();
        applyAnimations();
    }

    private void initializeViews() {
        etTeacherName = findViewById(R.id.etTeacherName);
        etPassword = findViewById(R.id.etPassword);
        subjectDropdown = findViewById(R.id.subjectDropdown);
        campusDropdown = findViewById(R.id.campusDropdown);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnBulkSubmit = findViewById(R.id.btnBulkSubmit);
        bulkUploadCard = findViewById(R.id.bulkUploadCard);
    }

    private void setupDropdowns() {
        // Sample data - replace with your actual data
        ArrayList<String> subjects = new ArrayList<>(Arrays.asList(
                "Mathematics", "Physics", "Chemistry", "Biology", "English",
                "History", "Geography", "Computer Science"
        ));

        ArrayList<String> campuses = new ArrayList<>(Arrays.asList(
                "Main Campus", "North Campus", "South Campus", "East Campus"
        ));

        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_item,
                subjects
        );

        ArrayAdapter<String> campusAdapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_item,
                campuses
        );

        subjectDropdown.setAdapter(subjectAdapter);
        campusDropdown.setAdapter(campusAdapter);
    }

    private void setupClickListeners() {
        btnSubmit.setOnClickListener(v -> validateAndSubmit());

        btnBulkSubmit.setOnClickListener(v -> {
            // Implement file picker logic here
            Toast.makeText(this, "Select Excel or CSV file", Toast.LENGTH_SHORT).show();
        });

        bulkUploadCard.setOnClickListener(v -> {
            // Implement file picker logic here
            btnBulkSubmit.performClick();
        });
    }

    private void applyAnimations() {
        // Add subtle animation to the bulk upload card
//        bulkUploadCard.setStateListAnimator(android.animation.AnimatorInflater
//                .loadStateListAnimator(this, R.animator.card_lift));


    }

    private void validateAndSubmit() {
        if (!validateFields()) {
            return;
        }

        // Get the input values
        String teacherName = etTeacherName.getText().toString();
        String subject = subjectDropdown.getText().toString();
        String campus = campusDropdown.getText().toString();
        String password = etPassword.getText().toString();

        // TODO: Implement your registration logic here
        // For now, just show a success message
        Toast.makeText(this, "Teacher registered successfully!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (etTeacherName.getText().toString().trim().isEmpty()) {
            ((TextInputLayout) etTeacherName.getParent().getParent()).setError("Name is required");
            isValid = false;
        }

        if (subjectDropdown.getText().toString().trim().isEmpty()) {
            ((TextInputLayout) subjectDropdown.getParent().getParent()).setError("Subject is required");
            isValid = false;
        }

        if (campusDropdown.getText().toString().trim().isEmpty()) {
            ((TextInputLayout) campusDropdown.getParent().getParent()).setError("Campus is required");
            isValid = false;
        }

        if (etPassword.getText().toString().trim().isEmpty()) {
            ((TextInputLayout) etPassword.getParent().getParent()).setError("Password is required");
            isValid = false;
        } else if (etPassword.getText().toString().length() < 6) {
            ((TextInputLayout) etPassword.getParent().getParent()).setError("Password must be at least 6 characters");
            isValid = false;
        }

        return isValid;
    }
}