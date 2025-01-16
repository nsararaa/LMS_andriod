package Models;

// Using Calendar for broader Android compatibility
import java.util.Calendar;

public class Subject {
    private int subjectId;
    private String subjectName;
    private String day;
    private Calendar time;  // Using Calendar for time representation
    private int teacherId;
    private int campusId;
    private int year;
    private String name; // Added to store teacher name
    private int studentCount; // Added to track the number of students

    public Subject(String subjectName, String name, int studentCount) {
        this.subjectName = subjectName;
        this.name = name;
        this.studentCount = studentCount;

        // Default values for optional fields
        this.subjectId = 0;
        this.day = null;
        this.time = null;
        this.teacherId = 0;
        this.campusId = 0;
        this.year = 0;
    }
    private Subject(SubjectBuilder builder) {
        this.subjectId = builder.subjectId;
        this.subjectName = builder.subjectName;
        this.day = builder.day;
        this.time = builder.time;
        this.teacherId = builder.teacherId;
        this.campusId = builder.campusId;
        this.year = builder.year;
        this.name = builder.name;
        this.studentCount = builder.studentCount;
    }

    public static class SubjectBuilder {
        private int subjectId;
        private String subjectName;
        private String day;
        private Calendar time;  // Using Calendar for time representation
        private int teacherId;
        private int campusId;
        private int year;
        private String name;
        private int studentCount; // Added to track the number of students

        public SubjectBuilder(String subjectName) {
            this.subjectName = subjectName;
        }

        public SubjectBuilder subjectId(int subjectId) {
            this.subjectId = subjectId;
            return this;
        }


        public SubjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SubjectBuilder day(String day) {
            this.day = day;
            return this;
        }


        public SubjectBuilder time(int hourOfDay, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            this.time = calendar;
            return this;
        }

        public SubjectBuilder teacherId(int teacherId) {
            this.teacherId = teacherId;
            return this;
        }

        public SubjectBuilder campusId(int campusId) {
            this.campusId = campusId;
            return this;
        }

        public SubjectBuilder year(int year) {
            this.year = year;
            return this;
        }

        public SubjectBuilder studentCount(int studentCount) {
            this.studentCount = studentCount;
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }
    }

    // Getters
    public int getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getDay() {
        return day;
    }

    public Calendar getTime() {
        return time;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getCampusId() {
        return campusId;
    }

    public int getYear() {
        return year;
    }

    public String getTeacherName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
