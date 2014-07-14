package edu.gatech.GTTutors.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import edu.gatech.GTTutors.model.LoginStore;

public class MenuController extends AbstractController {

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
        LoginStore.logOut();
        transition(event, "login");
    }

    @Override
    protected void populate(ActionEvent event) {
        // not needed
    }

}