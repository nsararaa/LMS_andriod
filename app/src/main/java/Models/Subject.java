package Models;


import java.time.LocalTime;

public class Subject {
    private int subjectId;
    private String subjectName;
    private String day;
    private LocalTime time;
    private int teacherId;
    private int campusId;
    private int year;

    private Subject(SubjectBuilder builder) {
        this.subjectId = builder.subjectId;
        this.subjectName = builder.subjectName;
        this.day = builder.day;
        this.time = builder.time;
        this.teacherId = builder.teacherId;
        this.campusId = builder.campusId;
        this.year = builder.year;
    }

    public static class SubjectBuilder {
        private int subjectId;
        private String subjectName;
        private String day;
        private LocalTime time;
        private int teacherId;
        private int campusId;
        private int year;

        public SubjectBuilder(String subjectName) {
            this.subjectName = subjectName;
        }

        public SubjectBuilder subjectId(int subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public SubjectBuilder day(String day) {
            this.day = day;
            return this;
        }

        public SubjectBuilder time(LocalTime time) {
            this.time = time;
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

        public Subject build() {
            return new Subject(this);
        }
    }

    // Getters
    public int getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public String getDay() { return day; }
    public LocalTime getTime() { return time; }
    public int getTeacherId() { return teacherId; }
    public int getCampusId() { return campusId; }
    public int getYear() { return year; }
}

