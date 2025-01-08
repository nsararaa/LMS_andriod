package Attendance;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;

import java.util.List;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.ViewHolder> {
    private List<StudentAttendance> studentList;

    public StudentAttendanceAdapter(List<StudentAttendance> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentAttendance student = studentList.get(position);
        holder.studentNameText.setText(student.getName());
        holder.attendanceCheckBox.setChecked(student.isPresent());

        holder.attendanceCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            student.setPresent(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public List<StudentAttendance> getAttendanceList() {
        return studentList;
    }

    public void updateStudentList(List<StudentAttendance> newList) {
        this.studentList = newList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentNameText;
        CheckBox attendanceCheckBox;

        ViewHolder(View itemView) {
            super(itemView);
            studentNameText = itemView.findViewById(R.id.studentNameText);
            attendanceCheckBox = itemView.findViewById(R.id.attendanceCheckBox);
        }
    }
}