package Models;

public class Teacher {
    private String name;
    private String email;
    private String phone;
    private float rating;
    private String[] subjects;
    private String[] feedback;

    public Teacher(String name, String email, String phone,
                   float rating, String[] subjects, String[] feedback) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.rating = rating;
        this.subjects = subjects;
        this.feedback = feedback;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public float getRating() {
        return rating;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public String[] getFeedback() {
        return feedback;
    }
}