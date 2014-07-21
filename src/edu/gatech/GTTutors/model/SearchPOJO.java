package edu.gatech.GTTutors.model;

public class SearchPOJO {
    
    private String name;
    private String email;
    private double avgProf;
    private int numProf;
    private double avgStudent;
    private int numStudent;

    public SearchPOJO(String name, String email, double avgProf, int numProf,
            double avgStudent, int numStudent) {
        this.name = name;
        this.email = email;
        this.avgProf = avgProf;
        this.numProf = numProf;
        this.avgStudent = avgStudent;
        this.numStudent = numStudent;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getAvgProf() {
        return avgProf;
    }

    public int getNumProf() {
        return numProf;
    }

    public double getAvgStudent() {
        return avgStudent;
    }

    public int getNumStudent() {
        return numStudent;
    }

}
