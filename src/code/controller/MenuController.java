package src.code.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import src.code.model.LoginStore;

public class MenuController extends AbstractController {

    @FXML
    private Text loginInfo;

    protected void setLoginDisplay(String username) {
        loginInfo.setText("Logged in as " + username);
    }

    @Override
    protected void submit(ActionEvent event) {
        String nextPage = ((Button)event.getSource()).getId();
        transition(event, nextPage);
    }

    @Override
    protected void goBack(ActionEvent event) {
        try {
            LoginStore.logOut();
            transition(event, "login");
        } catch (Exception e) {
            System.out.println("Something went wrong with loading the scene...: " + e);
        }
    }
    
    @Override
    protected void populate(ActionEvent event) {
        // TODO Auto-generated method stub
        
    }

}
