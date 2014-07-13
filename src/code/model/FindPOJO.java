package code.model;

public class FindPOJO {
    
    private String day;
    private String time;
    private String name;
    private String email;
    private String course;
    
    public FindPOJO(String day, String time, String name, String email,
            String course) {
        this.day = day;
        this.time = time;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }
}
