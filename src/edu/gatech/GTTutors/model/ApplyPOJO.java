package edu.gatech.GTTutors.model;

import javafx.scene.control.CheckBox;

public class ApplyPOJO {
    
    private String school;
    private String number;
    private CheckBox gta;
    private CheckBox select;
    
    public ApplyPOJO(String school, String number, CheckBox gta, CheckBox select) {
        super();
        this.school = school;
        this.number = number;
        this.gta = gta;
        this.select = select;
    }

    public String getSchool() {
        return school;
    }

    public String getNumber() {
        return number;
    }

    public CheckBox getGta() {
        return gta;
    }

    public CheckBox getSelect() {
        return select;
    }

}
