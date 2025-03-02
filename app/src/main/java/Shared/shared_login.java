package Shared;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import Admin.selectCampus;
import Instructor.TeacherDashboard;
import Student.StudentDashboard;

public class shared_login extends AppCompatActivity {
    private AutoCompleteTextView spinnerRole;
    private TextInputLayout tilRole;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_login);
        EdgeToEdge.enable(this);

        // Initialize views
        initializeViews();
        setupRoleSpinner();
    }

    private void initializeViews() {
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilRole = findViewById(R.id.tilRole);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        spinnerRole = findViewById(R.id.spinnerRole);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        android.widget.TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        android.widget.TextView tvSignUp = findViewById(R.id.tvSignUp);

        // Set up click listeners
        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(shared_login.this, "Contact administration", Toast.LENGTH_SHORT).show()
        );

        tvSignUp.setOnClickListener(v ->
                Toast.makeText(shared_login.this, "Contact administration to create an account", Toast.LENGTH_SHORT).show()
        );

        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void setupRoleSpinner() {
        String[] roles = new String[]{"Admin", "Teacher", "Student"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_item,  // Create this layout with a simple TextView
                roles
        );
        spinnerRole.setAdapter(adapter);
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String selectedRole = spinnerRole.getText().toString();

        // Validate inputs
        if (!validateInputs(email, password, selectedRole)) {
            return;
        }

        // Redirect to the selected role's dashboard
        redirectBasedOnRole(selectedRole);
    }

    private void redirectBasedOnRole(String role) {
        Intent intent = null;

        switch (role.toLowerCase()) {
            case "admin":
                intent = new Intent(shared_login.this, selectCampus.class);
                break;
            case "teacher":
                intent = new Intent(shared_login.this, TeacherDashboard.class);
                break;
            case "student":
                intent = new Intent(shared_login.this, StudentDashboard.class);
                break;
            default:
                Toast.makeText(this, "Unknown user role", Toast.LENGTH_SHORT).show();
                return;
        }

        if (intent != null) {
            startActivity(intent);
            finish(); // Close the login activity
        }
    }

    private boolean validateInputs(String email, String password, String role) {
        boolean isValid = true;

        if (email.isEmpty()) {
            tilEmail.setError("Email is required");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }

        if (password.isEmpty()) {
            tilPassword.setError("Password is required");
            isValid = false;
        } else {
            tilPassword.setError(null);
        }

        if (role.isEmpty()) {
            tilRole.setError("Role selection is required");
            isValid = false;
        } else {
            tilRole.setError(null);
        }

        return isValid;
    }
}
