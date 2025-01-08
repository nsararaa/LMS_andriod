package Admin.Subjects;

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
            // Handle FAB click, e.g., navigate to add a new subject
        });
    }

    // :TODO ADD ACTUAL DATA
    private void getData() {
        List<Subject> dummySubjects = new ArrayList<>();
        dummySubjects.add(new Subject("Mathematics", "John Doe", 30));
        dummySubjects.add(new Subject("Physics", "Jane Smith", 25));
        dummySubjects.add(new Subject("Chemistry", "Robert Johnson", 28));
        dummySubjects.add(new Subject("Biology", "Emily Davis", 35));
        dummySubjects.add(new Subject("History", "Michael Brown", 20));
        dummySubjects.add(new Subject("Geography", "Sarah Wilson", 18));


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
            subjectNameText.setText(subject.getName());
            teacherNameText.setText("Teacher: " + subject.getTeacherName());
            studentCountText.setText(subject.getStudentCount() + " Students");
        }
    }
}

class Subject {
    private String name;
    private String teacherName;
    private int studentCount;

    public Subject(String name, String teacherName, int studentCount) {
        this.name = name;
        this.teacherName = teacherName;
        this.studentCount = studentCount;
    }

    public String getName() {
        return name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
