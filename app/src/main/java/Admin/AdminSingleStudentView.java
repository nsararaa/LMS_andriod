package Admin;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.lms.R;

public class AdminSingleStudentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_single_student_view);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Student Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TextView personalDetailsText = findViewById(R.id.personalDetailsText);
        ListView subjectsListView = findViewById(R.id.subjectsListView);
        TextView attendanceText = findViewById(R.id.attendanceText);
        TextView resultsText = findViewById(R.id.resultsText);
        TextView finesText = findViewById(R.id.finesText);



        //SETTERS FOR TV
        personalDetailsText.setText(getPersonalDetails());
        ArrayAdapter<String> subjectsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                getSubjectsEnrolled()
        );
        subjectsListView.setAdapter(subjectsAdapter);
        attendanceText.setText(getAttendanceHistory());
        resultsText.setText(getResultReports());
        finesText.setText(getFinesDues());


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    //  get from db TODO DB
    private String getPersonalDetails() {
        return "Name: S \nID: 12345\nClass: X-A\nContact: +1234567890";
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


}