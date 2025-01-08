package Admin;

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

public class AdminTeacherView extends AppCompatActivity {
    private TextView teacherName, teacherEmail, teacherPhone;
    private ListView subjectsList, feedbackList;
    private RatingBar teacherRating;
    private Button viewStudentsButton;
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
        viewStudentsButton = findViewById(R.id.viewStudentsButton);


    }


    private void loadTeacherData() {
        // TODO: Replace with actual data fetching
        TeacherModel teacher = getTeacherData(teacherId);

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
        viewStudentsButton.setOnClickListener(v -> {
            // TODO: SINGLE STUDENT VIEW REQ
//            Intent intent = new Intent(this, ListView.class);
//            intent.putExtra("teacherId", teacherId);
//            startActivity(intent);
        });

        subjectsList.setOnItemClickListener((parent, view, position, id) -> {
            String subject = subjectsAdapter.getItem(position);
            showSubjectDetails(subject);
        });
    }

    private TeacherModel getTeacherData(String teacherId) {
        // TODO: Replace with actual API/database call
        return new TeacherModel(
                "John Doe",
                "john.doe@school.com",
                "+1234567890",
                4.5f,
                new String[]{"Mathematics", "Physics"},
                new String[]{"Great teacher!", "Very helpful"}
        );
    }

    private void showSubjectDetails(String subject) {
        // TODO: Implement subject details dialog or activity
    }
}

class TeacherModel {
    private String name, email, phone;
    private float rating;
    private String[] subjects;
    private String[] feedback;

    public TeacherModel(String name, String email, String phone,
                        float rating, String[] subjects, String[] feedback) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rating = rating;
        this.subjects = subjects;
        this.feedback = feedback;
    }


    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public float getRating() { return rating; }
    public String[] getSubjects() { return subjects; }
    public String[] getFeedback() { return feedback; }
}