package Models;

import java.util.ArrayList;
import java.util.List;

public class SubjectAssessment {
    private String subject;
    private double[] weeklyMarks;  // Array to store weekly marks
    private double averageWeekly;
    private double monthlyMarks;
    private double totalMarks;

    public SubjectAssessment(String subject, double... marks) {
        this.subject = subject;
        this.weeklyMarks = new double[marks.length];
        System.arraycopy(marks, 0, this.weeklyMarks, 0, marks.length);
        calculateAverages();
    }

    private void calculateAverages() {
        double sum = 0;
        for (double mark : weeklyMarks) {
            sum += mark;
        }
        this.averageWeekly = weeklyMarks.length > 0 ? sum / weeklyMarks.length : 0;
        this.monthlyMarks = averageWeekly; // You can modify this calculation as needed
        this.totalMarks = monthlyMarks; // You can modify this calculation as needed
    }

    // Getters
    public String getSubject() {
        return subject;
    }

    public double getWeeklyMark(int week) {
        if (week >= 0 && week < weeklyMarks.length) {
            return weeklyMarks[week];
        }
        return 0.0;
    }

    public double[] getAllWeeklyMarks() {
        return weeklyMarks.clone(); // Return a copy to maintain encapsulation
    }

    public int getNumberOfWeeks() {
        return weeklyMarks.length;
    }

    public double getAverageWeekly() {
        return averageWeekly;
    }

    public double getMonthlyMarks() {
        return monthlyMarks;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    // Setter for updating weekly marks
    public void updateWeeklyMark(int week, double mark) {
        if (week >= 0 && week < weeklyMarks.length) {
            weeklyMarks[week] = mark;
            calculateAverages();
        }
    }
}