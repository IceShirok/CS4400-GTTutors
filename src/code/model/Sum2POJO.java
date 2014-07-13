package code.model;

public class Sum2POJO {
    
    private String course;
    private String semester;
    private int numTa;
    private double avgTa;
    private int numNot;
    private double avgNot;
    
    public Sum2POJO(String course, String semester, int numTa, double avgTa,
            int numNot, double avgNot) {
        this.course = course;
        this.semester = semester;
        this.numTa = numTa;
        this.avgTa = avgTa;
        this.numNot = numNot;
        this.avgNot = avgNot;
    }
    
    public String getCourse() {
        return course;
    }
    public String getSemester() {
        return semester;
    }
    public int getNumTa() {
        return numTa;
    }
    public double getAvgTa() {
        return avgTa;
    }
    public int getNumNot() {
        return numNot;
    }
    public double getAvgNot() {
        return avgNot;
    }

}
