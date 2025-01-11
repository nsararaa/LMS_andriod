package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.lms.R;

import Admin.Subjects.SubjectDashboard;
import Shared.Attendance.AttendanceDashboard;
import Admin.Fee.FeeDashboard;
import Shared.SharedList;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener {

    private String campusName = "School Management";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_campus_view);

        // Get campus name from intent
        campusName = getIntent().getStringExtra("campusName");
        TextView header = findViewById(R.id.header);
        header.setText(campusName);



        CardView studentManagementCard = findViewById(R.id.student_management_card);
        CardView teacherManagementCard = findViewById(R.id.teacher_management_card);
        CardView subjectsCurriculumCard = findViewById(R.id.subject_management_card);
        CardView attendanceCard = findViewById(R.id.attendance_management_card);
        CardView resultsCard = findViewById(R.id.result_management_card);
        CardView feeManagementCard = findViewById(R.id.fee_management_card);
        CardView alumniCard = findViewById(R.id.alumni_management_card);


        studentManagementCard.setOnClickListener(this);
        teacherManagementCard.setOnClickListener(this);
        subjectsCurriculumCard.setOnClickListener(this);
        attendanceCard.setOnClickListener(this);
        resultsCard.setOnClickListener(this);
        feeManagementCard.setOnClickListener(this);
        alumniCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.student_management_card) {
            navigateToActivity(SharedList.class, "students");
        } else if (viewId == R.id.teacher_management_card) {
            navigateToActivity(SharedList.class, "teachers");
        } else if (viewId == R.id.subject_management_card) {
            navigateToActivity(SubjectDashboard.class, "Subjects and Curriculum");
        } else if (viewId == R.id.attendance_management_card) {
            navigateToActivity(AttendanceDashboard.class, "Attendance Management");
        } else if (viewId == R.id.result_management_card) {
            handleUnimplementedFeature("Results");
        } else if (viewId == R.id.fee_management_card) {
            navigateToActivity(FeeDashboard.class, "Attendance Management");

        } else if (viewId == R.id.alumni_management_card) {
            handleUnimplementedFeature("Alumni");
        }
    }

    private void navigateToActivity(Class<?> targetActivity, String managementType) {
        Intent intent = new Intent(this, targetActivity);
        intent.putExtra("type", managementType);
        intent.putExtra("Campus Name", campusName);
        startActivity(intent);
    }

    private void handleUnimplementedFeature(String featureName) {


    }
}