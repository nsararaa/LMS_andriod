package Shared;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lms.R;

import Models.MonthlyAssessment;
import Models.SubjectAssessment;

public class SingleResult extends AppCompatActivity {
    private TableLayout tableAssessment;
    private TextView tvMonthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_result);

        initializeViews();

        // For testing, use dummy data
        setAssessmentData(getDummyData());
    }

    private void initializeViews() {
        tableAssessment = findViewById(R.id.tableAssessment);
        tvMonthYear = findViewById(R.id.tvMonthYear);
    }

    private MonthlyAssessment getDummyData() {
        MonthlyAssessment monthlyAssessment = new MonthlyAssessment("January 2025");

        // Adding different subjects with their weekly marks
        monthlyAssessment.addAssessment(new SubjectAssessment("Mathematics", 85.5, 90.0, 88.5));
        monthlyAssessment.addAssessment(new SubjectAssessment("Physics", 82.0, 87.5, 84.0));
        monthlyAssessment.addAssessment(new SubjectAssessment("Chemistry", 78.5, 88.0, 92.5));
        monthlyAssessment.addAssessment(new SubjectAssessment("Biology", 90.0, 85.5, 87.0));
        monthlyAssessment.addAssessment(new SubjectAssessment("English", 92.5, 88.0, 90.5));
        monthlyAssessment.addAssessment(new SubjectAssessment("History", 88.0, 85.5, 89.0));
        monthlyAssessment.addAssessment(new SubjectAssessment("Computer Science", 95.0, 92.5, 94.0));

        return monthlyAssessment;
    }

    public void setAssessmentData(MonthlyAssessment monthlyAssessment) {
        tvMonthYear.setText(monthlyAssessment.getMonthYear());

        // Clear existing rows except header
        int childCount = tableAssessment.getChildCount();
        if (childCount > 1) {
            tableAssessment.removeViews(1, childCount - 1);
        }

        // Add subject rows
        for (SubjectAssessment assessment : monthlyAssessment.getAssessments()) {
            addSubjectRow(assessment);
        }
    }

    private void addSubjectRow(SubjectAssessment assessment) {
        TableRow row = new TableRow(this);
        row.setPadding(8, 8, 8, 8);
        row.setBackgroundColor(getResources().getColor(R.color.table_row_background));

        // Add subject name
        addCell(row, assessment.getSubject(), 120);

        // Add weekly marks
        double[] weeklyMarks = assessment.getAllWeeklyMarks();
        for (double mark : weeklyMarks) {
            addCell(row, String.format("%.1f", mark), 80);
        }

        // Add calculated values
        addCell(row, String.format("%.1f", assessment.getAverageWeekly()), 80);
        addCell(row, String.format("%.1f", assessment.getMonthlyMarks()), 80);
        addCell(row, String.format("%.1f", assessment.getTotalMarks()), 80);

        // Add alternating row background
        if (tableAssessment.getChildCount() % 2 == 0) {
            row.setBackgroundColor(getResources().getColor(R.color.background_tertiary));
        }

        tableAssessment.addView(row);
    }

    private void addCell(TableRow row, String text, int width) {
        TextView textView = new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                width,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        textView.setLayoutParams(params);
        textView.setText(text);
        textView.setTextColor(getResources().getColor(R.color.text_primary));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8, 8, 8, 8);

        // Apply cell style
        textView.setTextAppearance(R.style.TableCellStyle);
        row.addView(textView);
    }
}