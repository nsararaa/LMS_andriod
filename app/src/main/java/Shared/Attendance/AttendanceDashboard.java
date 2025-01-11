package Shared.Attendance;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class AttendanceDashboard extends AppCompatActivity {
    private MaterialButtonToggleGroup toggleGroup;
    private MaterialButton btnView;
    private MaterialButton btnMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggleGroup = findViewById(R.id.toggleGroup);
        btnView = findViewById(R.id.btnView);
        btnMark = findViewById(R.id.btnMark);


        setupToggleGroupListener();
    }

    private void setupToggleGroupListener() {
        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (!isChecked) return; // Ignore unchecking events

                Intent intent;
                if (checkedId == R.id.btnView) {

                    intent = new Intent(AttendanceDashboard.this, ViewAttendance.class);
                    startActivity(intent);
                } else if (checkedId == R.id.btnMark) {

                    intent = new Intent(AttendanceDashboard.this, MarkAttendance.class);
                    startActivity(intent);
                }
            }
        });
    }
}