package Admin;



import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Models.Teacher;

public class AdminTeacherView extends AppCompatActivity {
    private TextView teacherName, teacherEmail, teacherPhone;
    private ListView subjectsList, feedbackList;
    private RatingBar teacherRating;

    private ArrayAdapter<String> subjectsAdapter;
    private ArrayAdapter<String> feedbackAdapter;
    private String teacherId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher_view);

        teacherId = getIntent().getStringExtra("teacherId");
        initializeViews();
        loadTeacherData();
        setupListeners();

    }

    private void initializeViews() {
        teacherName = findViewById(R.id.teacherName);
        teacherEmail = findViewById(R.id.teacherEmail);
        teacherPhone = findViewById(R.id.teacherPhone);
        subjectsList = findViewById(R.id.subjectsList);
        feedbackList = findViewById(R.id.feedbackList);
        teacherRating = findViewById(R.id.teacherRating);



    }


    private void loadTeacherData() {
        // replace w actual fetching TODO
        Teacher teacher = getTeacherData(teacherId);

        teacherName.setText(teacher.getName());
        teacherEmail.setText(teacher.getEmail());
        teacherPhone.setText(teacher.getPhone());
        teacherRating.setRating(teacher.getRating());

        subjectsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, teacher.getSubjects());
        subjectsList.setAdapter(subjectsAdapter);

        feedbackAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, teacher.getFeedback());
        feedbackList.setAdapter(feedbackAdapter);
    }

    private void setupListeners() {
//        viewStudentsButton.setOnClickListener(v -> {
//            // TODO: SINGLE STUDENT VIEW REQ
////            Intent intent = new Intent(this, ListView.class);
////            intent.putExtra("teacherId", teacherId);
////            startActivity(intent);
//        });

        subjectsList.setOnItemClickListener((parent, view, position, id) -> {
            String subject = subjectsAdapter.getItem(position);
            showSubjectDetails(subject);
        });
    }

    private Teacher getTeacherData(String teacherId) {
        //db call TODO
        return new Teacher(
                "Sarfraz raza",
                "sarfraz@school.com",
                "+1234567890",
                4.5f,
                new String[]{"Mathematics", "Physics"},
                new String[]{ "Very helpful"}
        );
    }

    private void showSubjectDetails(String subject) {
        // TODO chnage intent?
        Intent i = new Intent(AdminTeacherView.this, Shared.SubjectView.class);
        i.putExtra("SubjectName", subject);
        startActivity(i);



    }
}

