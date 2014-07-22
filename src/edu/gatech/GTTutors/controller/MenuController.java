package edu.gatech.GTTutors.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import edu.gatech.GTTutors.main.GTTutorsLaunch;

public class MenuController extends AbstractController {
    
    @FXML
    private TabPane menu;
    @FXML
    private Tab professor;
    @FXML
    private Tab administrator;
    @FXML
    private Tab student;

    @FXML
    private Button search;
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
        
        professor.setDisable(true);
        administrator.setDisable(true);
        student.setDisable(true);
        
        String user = GTTutorsLaunch.log.getUserType();
        if(user.equals("Administrator")) {
            administrator.setDisable(false);
            menu.getSelectionModel().select(administrator);
        } else if(user.equals("Professor")) {
            professor.setDisable(false);
            menu.getSelectionModel().select(professor);
        } else {
            student.setDisable(false);
            menu.getSelectionModel().select(student);
        }
        
        find.setVisible(user.equals("Tutor"));
    }

}
