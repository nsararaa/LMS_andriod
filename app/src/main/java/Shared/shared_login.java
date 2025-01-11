package Shared;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import Admin.admin_select_campus;

public class shared_login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_login);
        EdgeToEdge.enable(this);

        // Initialize views with new IDs
        TextInputLayout tilEmail = findViewById(R.id.tilEmail);
        TextInputLayout tilPassword = findViewById(R.id.tilPassword);
        TextInputEditText etEmail = findViewById(R.id.etEmail);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        android.widget.TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        android.widget.TextView tvSignUp = findViewById(R.id.tvSignUp);

        // Set up forgot password click listener
        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(shared_login.this, "Contact administration", Toast.LENGTH_SHORT).show()
        );

        // Set up sign up click listener
        tvSignUp.setOnClickListener(v ->
                Toast.makeText(shared_login.this, "Contact administration to create an account", Toast.LENGTH_SHORT).show()
        );

        // Set up login button click listener
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                tilEmail.setError("Email is required");
                return;
            } else {
                tilEmail.setError(null);
            }

            if (password.isEmpty()) {
                tilPassword.setError("Password is required");
                return;
            } else {
                tilPassword.setError(null);
            }

            // Here you would typically implement your authentication logic
            // For now, we'll just redirect to admin dashboard as an example
            Intent adminIntent = new Intent(shared_login.this, admin_select_campus.class);
            startActivity(adminIntent);

            // Uncomment and modify these sections when implementing other roles
//            switch (userRole) {
//                case "Instructor":
//                    Intent instructorIntent = new Intent(shared_login.this, InstructorDashboard.class);
//                    startActivity(instructorIntent);
//                    break;
//                case "Student":
//                    Intent studentIntent = new Intent(shared_login.this, StudentDashboard.class);
//                    startActivity(studentIntent);
//                    break;
//                default:
//                    Toast.makeText(shared_login.this, "Invalid role", Toast.LENGTH_SHORT).show();
//                    break;
//            }

        });
    }
}