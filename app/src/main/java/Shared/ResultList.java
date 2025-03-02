package Shared;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lms.R;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.Filter;
import android.widget.Filterable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import Models.Student;

public class ResultList extends AppCompatActivity {
    private ListView studentListView;
    private List<Student> studentList;
    private StudentAdapter adapter;
    private TextInputEditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);


        studentListView = findViewById(R.id.studentListView);
        searchEditText = findViewById(R.id.searchEditText);


        studentList = new ArrayList<>();
        addDummyData(); //TODO DB CALL
        adapter = new StudentAdapter(this, studentList);
        studentListView.setAdapter(adapter);


        studentListView.setOnItemClickListener((parent, view, position, id) -> {
            Student student = studentList.get(position);
            openStudentDetails(student);
        });


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    //TODO DB CALL
    private void addDummyData() {
        studentList.add(new Student(1, "Sara", "1234567890", 1));
        studentList.add(new Student(2, "M", "9876543210", 2));

    }

    private void openStudentDetails(Student student) {
        Intent intent = new Intent(this, SingleResult.class);
        intent.putExtra("STUDENT_ID", student.getStudentId());
        intent.putExtra("STUDENT_NAME", student.getStudentName());
        intent.putExtra("STUDENT_PHONE", student.getPhoneNumber());
        intent.putExtra("STUDENT_YEAR", student.getYear());
        startActivity(intent);
    }
}

class StudentAdapter extends ArrayAdapter<Student> implements Filterable {
    private List<Student> originalList;
    private List<Student> filteredList;
    private StudentFilter filter;
    private Context context;

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
        this.context = context;
        this.originalList = new ArrayList<>(students);
        this.filteredList = new ArrayList<>(students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.student_res_list_item, parent, false);
        }

        Student student = getItem(position);

        TextView nameText = convertView.findViewById(R.id.studentName);
        TextView idText = convertView.findViewById(R.id.studentId);
        TextView yearText = convertView.findViewById(R.id.year);

        nameText.setText(student.getStudentName());
        idText.setText(String.valueOf(student.getStudentId()));
        yearText.setText("Year " + student.getYear());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new StudentFilter();
        }
        return filter;
    }

    private class StudentFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Student> filteredItems = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredItems.addAll(originalList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Student student : originalList) {
                    if (student.getStudentName().toLowerCase().contains(filterPattern) ||
                            String.valueOf(student.getStudentId()).contains(filterPattern)) {
                        filteredItems.add(student);
                    }
                }
            }

            results.values = filteredItems;
            results.count = filteredItems.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<Student>) results.values;
            clear();
            addAll(filteredList);
            notifyDataSetChanged();
        }
    }
}