package Shared.Attendance;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lms.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Models.StudentAttendance;

public class MarkAttendance extends AppCompatActivity {
    private TextInputEditText datePickerEdit;
    private AutoCompleteTextView classSpinner;
    private RecyclerView studentsRecyclerView;
    private MaterialButton saveButton;
    private StudentAttendanceAdapter attendanceAdapter;
    private SimpleDateFormat dateFormatter;

    private static final String DATE_PICKER_TAG = "DATE_PICKER";


    void initViews(){
        datePickerEdit = findViewById(R.id.datePickerEdit);
        classSpinner = findViewById(R.id.classSpinner);
        studentsRecyclerView = findViewById(R.id.studentsRecyclerView);
        saveButton = findViewById(R.id.saveButton);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);


        initViews();


        dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        // init UI
        setupDatePicker();
        setupClassSpinner();
        setupRecyclerView();
        setupSaveButton();
    }

    private void setupDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePickerEdit.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(), DATE_PICKER_TAG));

        datePicker.addOnPositiveButtonClickListener(selection -> {
            String formattedDate = dateFormatter.format(new Date(selection));
            datePickerEdit.setText(formattedDate);
            loadStudentsForDate(formattedDate);
        });



        datePickerEdit.setText(dateFormatter.format(new Date()));
    }

    // GET FROM DB TODO:
    String[] getSubjects(){
        String[] subs = {"Physics", "math", "urdu"};
        return subs;
    }

    private void setupClassSpinner() {
        String[] classes = getSubjects();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                classes
        );
        classSpinner.setAdapter(adapter);

        classSpinner.setOnItemClickListener((parent, view, position, id) -> {
            String selectedClass = classes[position];
            loadStudentsForClass(selectedClass);
        });
    }

    private void setupRecyclerView() {
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceAdapter = new StudentAttendanceAdapter(new ArrayList<>());
        studentsRecyclerView.setAdapter(attendanceAdapter);
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> saveAttendance());
    }

    private void saveAttendance() {
        String date = datePickerEdit.getText().toString();
        String selectedClass = classSpinner.getText().toString();

        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(selectedClass)) {
            Toast.makeText(this, "Please select a subject", Toast.LENGTH_SHORT).show();
            return;
        }

        List<StudentAttendance> attendanceList = attendanceAdapter.getAttendanceList();

        // Save attendance TODO:
        Toast.makeText(this, "Attendance saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void loadStudentsForClass(String className) {
        //  Replace with actual data TODO:
        List<StudentAttendance> studentList = DummyStudentData.getStudentsForClass(className, datePickerEdit.getText().toString());
        attendanceAdapter = new StudentAttendanceAdapter(studentList);
        studentsRecyclerView.setAdapter(attendanceAdapter);
    }

    private void loadStudentsForDate(String date) {
        String currentClass = classSpinner.getText().toString();
        if (!TextUtils.isEmpty(currentClass)) {
            loadStudentsForClass(currentClass);
        }
    }
}

// DUMMY REMOVE LATER
class DummyStudentData {
    static List<StudentAttendance> getStudentsForClass(String className, String date) {
        List<StudentAttendance> students = new ArrayList<>();

        switch (className) {
            case "Class 1":
                students.add(new StudentAttendance(1001, "John Smith", "STU001", true, date));
                students.add(new StudentAttendance(1002, "Sarah Johnson", "STU002", true, date));

                break;
            case "Class 2":
                students.add(new StudentAttendance(2001, "Emma Thompson", "STU006", true, date));

                break;
            case "Class 3":
                students.add(new StudentAttendance(3001, "Lucas Martin", "STU011", true, date));

                break;
            case "Class 4":
                students.add(new StudentAttendance(4001, "Ethan Wright", "STU016", true, date));

                break;
        }

        return students;
    }
}