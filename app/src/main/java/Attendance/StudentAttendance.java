package Attendance;


public class StudentAttendance {
    private String id;
    private String name;
    private boolean present;

    public StudentAttendance(String id, String name) {
        this.id = id;
        this.name = name;
        this.present = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}