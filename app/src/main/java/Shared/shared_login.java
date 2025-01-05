package Shared;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lms.R;

import Admin.admin_select_campus;

//import Admin.AdminDashboard;
//import Instructor.InstructorDashboard;
//import Student.StudentDashboard;

public class shared_login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_login);
        EdgeToEdge.enable(this);

        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        TextView forgotPasswordText = findViewById(R.id.forgotPasswordText);
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        Button loginButton = findViewById(R.id.loginButton);

        String[] roles = {"Admin", "Instructor", "Student"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        roleSpinner.setAdapter(adapter);

        forgotPasswordText.setOnClickListener(v ->
                Toast.makeText(shared_login.this, "Contact administration", Toast.LENGTH_SHORT).show()
        );

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String role = roleSpinner.getSelectedItem().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(shared_login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                switch (role) {
                    case "Admin":
                         Intent adminIntent = new Intent(shared_login.this, admin_select_campus.class);
                        startActivity(adminIntent);
                        break;

                    case "Instructor":
//                        Intent instructorIntent = new Intent(login.this, InstructorDashboard.class);
//                        startActivity(instructorIntent);
//                        break;

                    case "Student":
//                        Intent studentIntent = new Intent(login.this, StudentDashboard.class);
//                        startActivity(studentIntent);
//                        break;

                    default:
                        Toast.makeText(shared_login.this, "Invalid role selected", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
