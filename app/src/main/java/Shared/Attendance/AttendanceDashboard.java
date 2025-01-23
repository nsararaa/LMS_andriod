package Shared.Attendance;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lms.R;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class AttendanceDashboard extends AppCompatActivity {
    private CircularProgressIndicator presentProgress;
    private CircularProgressIndicator absentProgress;
    private TextView tvPresentCount;
    private TextView tvAbsentCount;
    private LinearLayout btnMarkAttendance;
    private LinearLayout btnViewRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        initializeViews();
        setupClickListeners();
        loadAttendanceData();
    }

    private void initializeViews() {
        presentProgress = findViewById(R.id.presentProgress);
        absentProgress = findViewById(R.id.absentProgress);
        tvPresentCount = findViewById(R.id.tvPresentCount);
        tvAbsentCount = findViewById(R.id.tvAbsentCount);
        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);
        btnViewRecords = findViewById(R.id.btnViewRecords);
    }

    private void setupClickListeners() {
        btnMarkAttendance.setOnClickListener(v -> launchMarkAttendance());
        btnViewRecords.setOnClickListener(v -> launchViewRecords());
    }

    private void loadAttendanceData() {
        int totalStudents = 50;
        int presentStudents = 42;
        int absentStudents = 8;

        int presentPercentage = (presentStudents * 100) / totalStudents;
        int absentPercentage = (absentStudents * 100) / totalStudents;

        updateAttendanceDisplay(presentStudents, absentStudents, totalStudents,
                presentPercentage, absentPercentage);
    }

    private void updateAttendanceDisplay(int presentCount, int absentCount,
                                         int totalCount, int presentPercentage,
                                         int absentPercentage) {
        presentProgress.setProgress(presentPercentage);
        absentProgress.setProgress(absentPercentage);

        tvPresentCount.setText(String.format("%d/%d Students", presentCount, totalCount));
        tvAbsentCount.setText(String.format("%d/%d Students", absentCount, totalCount));
    }

    private void launchMarkAttendance() {
        Intent i = new Intent(AttendanceDashboard.this, MarkAttendance.class);
        startActivity(i);
    }

    private void launchViewRecords() {
        Intent i = new Intent(AttendanceDashboard.this, ViewAttendance.class);
        startActivity(i);
    }
}