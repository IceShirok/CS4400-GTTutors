package src.code.model;

public class Sum1POJO {
    
    private String course;
    private String semester;
    private int numStudents;
    private int numTutors;
    
    public Sum1POJO(String course, String semester, int numStudents,
            int numTutors) {
        this.course = course;
        this.semester = semester;
        this.numStudents = numStudents;
        this.numTutors = numTutors;
    }
    
    public String getCourse() {
        return course;
    }
    public String getSemester() {
        return semester;
    }
    public int getNumStudents() {
        return numStudents;
    }
    public int getNumTutors() {
        return numTutors;
    }

}
