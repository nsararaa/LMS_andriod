package Attendance;
import android.os.Bundle;
import android.view.View;
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
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MarkAttendance extends AppCompatActivity {
    private TextInputEditText datePickerEdit;
    private AutoCompleteTextView classSpinner;
    private RecyclerView studentsRecyclerView;
    private MaterialButton saveButton;
    private StudentAttendanceAdapter attendanceAdapter;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        // Initialize views
        datePickerEdit = findViewById(R.id.datePickerEdit);
        classSpinner = findViewById(R.id.classSpinner);
        studentsRecyclerView = findViewById(R.id.studentsRecyclerView);
        saveButton = findViewById(R.id.saveButton);

        // Setup date formatter
        dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        // Setup date picker
        setupDatePicker();

        // Setup class spinner
        setupClassSpinner();

        // Setup RecyclerView
        setupRecyclerView();

        // Setup save button
        setupSaveButton();
    }

    private void setupDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePickerEdit.setOnClickListener(v -> datePicker.show(getSupportFragmentManager(), "DATE_PICKER"));

        datePicker.addOnPositiveButtonClickListener(selection -> {
            String formattedDate = dateFormatter.format(new Date(selection));
            datePickerEdit.setText(formattedDate);
            loadStudentsForDate(formattedDate);
        });

        // Set current date by default
        datePickerEdit.setText(dateFormatter.format(new Date()));
    }

    private void setupClassSpinner() {
        // Sample class list - replace with your actual class data
        String[] classes = new String[]{"Class 1", "Class 2", "Class 3", "Class 4"};
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

    private void loadStudentsForDate(String date) {
        // TODO: Implement loading students for selected date
        // This should be implemented based on your data source
    }

    private void loadStudentsForClass(String className) {
        // TODO: Implement loading students for selected class
        // This should be implemented based on your data source
    }

    private void saveAttendance() {
        // Validate inputs
        if (datePickerEdit.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (classSpinner.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please select a class", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get attendance data from adapter
        List<StudentAttendance> attendanceList = attendanceAdapter.getAttendanceList();

        // TODO: Implement saving attendance to your data source
        // For now, just show a success message
        Toast.makeText(this, "Attendance saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
