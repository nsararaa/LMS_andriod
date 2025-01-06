package Shared;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;
import com.google.android.material.textfield.TextInputEditText;

import Admin.AdminStudentView;
import Admin.AdminTeacherView;

import java.util.ArrayList;
import java.util.Arrays;

public class SharedList extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private String[] allNames;
    private ArrayList<String> filteredList;
    private static final String TYPE_STUDENTS = "students";
    private static final String TYPE_TEACHERS = "teachers";

    private String type; // Declare `type` here

    private String[] getStudentNames() {
        // TODO: Replace with actual data source
        return new String[]{"John", "Alice", "Michael"};
    }

    private String[] getTeacherNames() {
        // TODO: Replace with actual data source
        return new String[]{"Ms. Smith", "Mr. Brown", "Ms. Green"};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_student_list);

        // Get the "type" from Intent
        type = getIntent().getStringExtra("type");

        // Setup window insets
        setupWindowInsets();

        // Initialize the list based on type
        initializeList();

        // Load the list into the UI
        loadList();
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeList() {
        // Populate names based on the type
        if (TYPE_STUDENTS.equals(type)) {
            allNames = getStudentNames();
        } else if (TYPE_TEACHERS.equals(type)) {
            allNames = getTeacherNames();
        } else {
            allNames = new String[0]; // Empty list for invalid type
        }
        filteredList = new ArrayList<>(Arrays.asList(allNames));
    }

    private void loadList() {
        // Initialize ListView and Search Bar
        ListView listView = findViewById(R.id.studentListView);
        TextInputEditText searchEditText = findViewById(R.id.searchEditText);

        // Create ArrayAdapter
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                filteredList
        );
        listView.setAdapter(adapter);

        // Setup search functionality
        setupSearchListener(searchEditText);

        // Handle item clicks
        setupItemClickListener(listView);
    }

    private void setupSearchListener(TextInputEditText searchEditText) {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filterItems(s.toString());
            }
        });
    }

    private void setupItemClickListener(ListView listView) {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = filteredList.get(position);

            Intent intent;
            if (TYPE_STUDENTS.equals(type)) {
                intent = new Intent(SharedList.this, AdminStudentView.class);
                intent.putExtra("selectedStudentKey", selectedItem);
            } else if (TYPE_TEACHERS.equals(type)) {
                intent = new Intent(SharedList.this, AdminTeacherView.class);
                intent.putExtra("selectedTeacherKey", selectedItem);
            } else {
                return; // Do nothing for invalid type
            }
            startActivity(intent);
        });
    }

    private void filterItems(String searchText) {
        filteredList.clear();
        if (searchText.isEmpty()) {
            filteredList.addAll(Arrays.asList(allNames));
        } else {
            String searchLower = searchText.toLowerCase().trim();
            for (String item : allNames) {
                if (item.toLowerCase().contains(searchLower)) {
                    filteredList.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}