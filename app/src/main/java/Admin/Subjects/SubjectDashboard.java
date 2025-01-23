package Admin.Subjects;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Admin.addSubject;
import Models.Subject;
public class SubjectDashboard extends AppCompatActivity {
    private RecyclerView subjectsRecyclerView;
    private SubjectsAdapter subjectsAdapter;
    private FloatingActionButton addSubjectFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_dashboard);

        initializeViews();
        setupRecyclerView();
        setupFabButton();


        getData();
    }

    private void initializeViews() {
        subjectsRecyclerView = findViewById(R.id.subjectsRecyclerView);
        addSubjectFab = findViewById(R.id.addSubjectFab);
    }

    private void setupRecyclerView() {
        subjectsAdapter = new SubjectsAdapter();
        subjectsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectsRecyclerView.setAdapter(subjectsAdapter);


    }

    private void setupFabButton() {
        addSubjectFab.setOnClickListener(v -> {
            Intent i = new Intent(SubjectDashboard.this, addSubject.class);
            startActivity(i);
        });
    }

    // :TODO ADD ACTUAL DATA
    private void getData() {
        List<Subject> dummySubjects = new ArrayList<>();
        dummySubjects.add(new Subject("Mathematics", "S", 30));
        dummySubjects.add(new Subject("Physics", "N", 25));
        dummySubjects.add(new Subject("Chemistry", "M", 28));
        dummySubjects.add(new Subject("Biology", "Z", 35));
        dummySubjects.add(new Subject("History", "K", 20));
        dummySubjects.add(new Subject("Geography", "L", 18));


        subjectsAdapter.setSubjects(dummySubjects);
    }
}

class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {
    private List<Subject> subjects = new ArrayList<>();

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.bind(subject);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    static class SubjectViewHolder extends RecyclerView.ViewHolder {
        private final TextView subjectNameText;
        private final TextView teacherNameText;
        private final TextView studentCountText;

        SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectNameText = itemView.findViewById(R.id.subjectNameText);
            teacherNameText = itemView.findViewById(R.id.teacherNameText);
            studentCountText = itemView.findViewById(R.id.studentCountText);
        }

        void bind(Subject subject) {
            subjectNameText.setText(subject.getTeacherName());
            teacherNameText.setText("Teacher: " + subject.getTeacherName());
            studentCountText.setText(subject.getStudentCount() + " Students");
        }
    }
}

