package edu.gatech.GTTutors.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import edu.gatech.GTTutors.main.GTTutorsLaunch;

public class MenuController extends AbstractController {

    @FXML
    private Button search;
    @FXML
    private Button schedule;
    @FXML
    private Button rate;
    
    @FXML
    private Button apply;
    @FXML
    private Button find;
    
    @FXML
    private Button recommend;
    
    @FXML
    private Button sum1;
    @FXML
    private Button sum2;
    
    
    @FXML
    private GridPane student;
    @FXML
    private GridPane tutorstudent;
    @FXML
    private GridPane professor;
    @FXML
    private GridPane administrator;
    
    @FXML
    private GridPane[] rows;
    
    @FXML
    protected void initialize() {
        rows = new GridPane[]{tutorstudent, student, professor, administrator};
    }

    @FXML
    private Label loginInfo;

    protected void setLoginDisplay(String username) {
        loginInfo.setText("Logged in as: " + username);
    }

    @Override
    protected void submit(ActionEvent event) {
        String nextPage = ((Button)event.getSource()).getId();
        transition(event, nextPage);
    }

    @Override
    protected void goBack(ActionEvent event) {
        GTTutorsLaunch.log.logOut();
        transition(event, "login");
    }

    @Override
    protected void populate(ActionEvent event) {
        setLoginDisplay(GTTutorsLaunch.log.getUsername());
        
        for(GridPane row : rows) {
            row.setVisible((row.getId().toLowerCase()).contains(GTTutorsLaunch.log.getUserType().toLowerCase()));
        }
    }

}
