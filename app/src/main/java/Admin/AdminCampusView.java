package Admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lms.R;
import androidx.appcompat.app.AppCompatActivity;

import Shared.SharedList;

public class AdminCampusView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_campus_view);


        Intent intent = getIntent();
        String campName = intent.getStringExtra("Campus Name");
        TextView header = findViewById(R.id.header);
        header.setText(campName);

        // Initialize buttons
        Button btnStudentManagement = findViewById(R.id.btn_student_management);
        Button btnTeacherManagement = findViewById(R.id.btn_teacher_management);
        Button btnSubjectsCurriculum = findViewById(R.id.btn_subjects_curriculum);
        Button btnAttendance = findViewById(R.id.btn_attendance);
        Button btnResults = findViewById(R.id.btn_results);
        Button btnFeeManagement = findViewById(R.id.btn_fee_management);
        Button btnAlumni = findViewById(R.id.btn_alumni);

        btnStudentManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(SharedList.class, "students");
            }
        });

        btnTeacherManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(SharedList.class, "teachers");
            }

        });

        btnSubjectsCurriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Subjects and Curriculum");
            }
        });

        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Attendance Management");
            }
        });

        btnResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Results");
            }
        });

        btnFeeManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Fee Management");
            }
        });

        btnAlumni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity("Alumni");
            }
        });
    }

    private void navigateToActivity( String managementType) {
//        Intent intent = new Intent(AdminCampusView.this, ManagementViewActivity.class);
//        intent.putExtra("type", managementType);
//        startActivity(intent);
    }
    // Helper method to navigate to another activity
    private void navigateToActivity(Class<?> targetActivity, String managementType) {
        Intent intent = new Intent(AdminCampusView.this, targetActivity);
        intent.putExtra("type", managementType);
        startActivity(intent);
    }
    private void navigateToActivity( Class<?> targetActivity) {
        Intent intent = new Intent(AdminCampusView.this, targetActivity);

        startActivity(intent);
    }
}