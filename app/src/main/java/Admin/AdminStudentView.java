package Admin;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lms.R;

public class AdminStudentView extends AppCompatActivity {

    // Mock data getter methods (replace with actual implementations)
    private String getPersonalDetails() {
        return "Name: John Doe\nID: 12345\nClass: X-A\nContact: +1234567890";
    }

    private String[] getSubjectsEnrolled() {
        return new String[]{"Mathematics", "Physics", "Chemistry", "English"};
    }

    private String getAttendanceHistory() {
        return "Total Classes: 100\nPresent: 85\nAbsent: 15\nAttendance: 85%";
    }

    private String getResultReports() {
        return "Mathematics: 85%\nPhysics: 78%\nChemistry: 92%\nEnglish: 88%\nOverall: 85.75%";
    }

    private String getFinesDues() {
        return "Library Fine: $10\nLab Fee Due: $50\nTotal Outstanding: $60";
    }

    private String[] getQueryHistory() {
        return new String[]{
                "2024-01-01: Requested transcript",
                "2024-01-15: Submitted leave application",
                "2024-02-01: Asked for fee structure"
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_view);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Student Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        TextView personalDetailsText = findViewById(R.id.personalDetailsText);
        ListView subjectsListView = findViewById(R.id.subjectsListView);
        TextView attendanceText = findViewById(R.id.attendanceText);
        TextView resultsText = findViewById(R.id.resultsText);
        TextView finesText = findViewById(R.id.finesText);
        ListView queryHistoryListView = findViewById(R.id.queryHistoryListView);

        // Set personal details
        personalDetailsText.setText(getPersonalDetails());

        // Set subjects
        ArrayAdapter<String> subjectsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getSubjectsEnrolled()
        );
        subjectsListView.setAdapter(subjectsAdapter);

        // Set attendance
        attendanceText.setText(getAttendanceHistory());

        // Set results
        resultsText.setText(getResultReports());

        // Set fines/dues
        finesText.setText(getFinesDues());

        // Set query history
        ArrayAdapter<String> queryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getQueryHistory()
        );
        queryHistoryListView.setAdapter(queryAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}