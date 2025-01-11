package Shared.Attendance;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import Models.StudentAttendance;

public class ViewAttendance extends AppCompatActivity {
    private RecyclerView rvAttendance;
    private TextView tvPresentCount, tvAbsentCount;
    private AttendanceAdapter adapter;
    private List<StudentAttendance> attendanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        // Initialize views
        rvAttendance = findViewById(R.id.rvAttendance);
        tvPresentCount = findViewById(R.id.tvPresentCount);
        tvAbsentCount = findViewById(R.id.tvAbsentCount);
        FloatingActionButton fabRefresh = findViewById(R.id.fabRefresh);

        // Setup RecyclerView
        rvAttendance.setLayoutManager(new LinearLayoutManager(this));
        attendanceList = getDummyData();
        adapter = new AttendanceAdapter(attendanceList);
        rvAttendance.setAdapter(adapter);

        // Update counts
        updateAttendanceCounts();

        fabRefresh.setOnClickListener(v -> refreshAttendance());
    }

    private void updateAttendanceCounts() {
        int presentCount = 0;
        for (StudentAttendance attendance : attendanceList) {
            if (attendance.isPresent()) {
                presentCount++;
            }
        }
        tvPresentCount.setText("Present: " + presentCount);
        tvAbsentCount.setText("Absent: " + (attendanceList.size() - presentCount));
    }

    private void refreshAttendance() {
        // Simulate refresh - replace with actual data fetch
        attendanceList = getDummyData();
        adapter.updateData(attendanceList);
        updateAttendanceCounts();
    }

    private List<StudentAttendance> getDummyData() {
        List<StudentAttendance> dummyData = new ArrayList<>();
        dummyData.add(new StudentAttendance(1001, "John Smith", "CS001", true, "2025-01-09"));
        dummyData.add(new StudentAttendance(1002, "Emma Johnson", "CS002", true, "2025-01-09"));
        dummyData.add(new StudentAttendance(1003, "Michael Brown", "CS003", false, "2025-01-09"));
        dummyData.add(new StudentAttendance(1004, "Sarah Davis", "CS004", true, "2025-01-09"));
        dummyData.add(new StudentAttendance(1005, "James Wilson", "CS005", false, "2025-01-09"));
        return dummyData;
    }

    private static class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
        private List<StudentAttendance> attendanceList;

        public AttendanceAdapter(List<StudentAttendance> attendanceList) {
            this.attendanceList = attendanceList;
        }

        public void updateData(List<StudentAttendance> newData) {
            this.attendanceList = newData;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.attendance_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            StudentAttendance attendance = attendanceList.get(position);
            holder.tvName.setText(attendance.getStudentName());
            holder.tvId.setText(attendance.getStudentId());
            holder.tvStatus.setText(attendance.isPresent() ? "Present" : "Absent");
            holder.tvStatus.setTextColor(holder.itemView.getContext().getColor(
                    attendance.isPresent() ? R.color.success_color : R.color.error_color));
        }

        @Override
        public int getItemCount() {
            return attendanceList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvId, tvStatus;

            ViewHolder(View view) {
                super(view);
                tvName = view.findViewById(R.id.tvName);
                tvId = view.findViewById(R.id.tvId);
                tvStatus = view.findViewById(R.id.tvStatus);
            }
        }
    }
}