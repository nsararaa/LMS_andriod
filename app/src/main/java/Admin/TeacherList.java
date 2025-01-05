package Admin;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;
import com.google.android.material.textfield.TextInputEditText;

import Admin.AdminStudentView;
import java.util.ArrayList;
import java.util.Arrays;

public class TeacherList extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private String[] allTeachers;
    private ArrayList<String> filteredList;

    String[] getTeacherNames(){
        String[] names = {"s", "a", "n"};
        return names;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_list);

        // Get window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ListView and Search
        ListView studentListView = findViewById(R.id.teacherListView);
        TextInputEditText searchEditText = findViewById(R.id.searchEditText);

        // Get all student names
        allTeachers = getTeacherNames();
        filteredList = new ArrayList<>(Arrays.asList(allTeachers));

        // Create adapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                filteredList
        );

        studentListView.setAdapter(adapter);

        // Setup search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterStudents(s.toString());
            }
        });

        // Maintain existing click listener
        studentListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedStudent = filteredList.get(position);
            Intent i = new Intent(Admin.TeacherList.this, AdminStudentView.class);
            i.putExtra("selectedStudentKey", selectedStudent);
            startActivity(i);
        });
    }
    //add rollnum/rfid
    private void filterStudents(String searchText) {
        filteredList.clear();
        if (searchText.isEmpty()) {
            filteredList.addAll(Arrays.asList(allTeachers));
        } else {
            String searchLower = searchText.toLowerCase().trim();
            for (String student : allTeachers) {
                if (student.toLowerCase().contains(searchLower)) {
                    filteredList.add(student);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}