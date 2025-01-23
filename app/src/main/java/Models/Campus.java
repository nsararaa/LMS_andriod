package Models;

public class Campus {
    private int campusID;
    private String campusName;

    // Constructor
    public Campus(int campusID, String campusName) {
        this.campusID = campusID;
        this.campusName = campusName;
    }

    // Getters
    public int getCampusID() {
        return campusID;
    }

    public String getCampusName() {
        return campusName;
    }

    // Setters
    public void setCampusID(int campusID) {
        this.campusID = campusID;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    // toString() Method (Optional: for easy representation)
    @Override
    public String toString() {
        return "Campus{" +
                "campusID=" + campusID +
                ", campusName='" + campusName + '\'' +
                '}';
    }
}