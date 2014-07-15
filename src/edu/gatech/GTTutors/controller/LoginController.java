package edu.gatech.GTTutors.controller;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import edu.gatech.GTTutors.main.DatabaseController;
import edu.gatech.GTTutors.model.LoginStore;

public class LoginController extends AbstractController {
    
    @FXML
    private Text message;

    @FXML
    private TextField gtid;

    @FXML
    private PasswordField pw;

    @FXML
    protected void submit(ActionEvent event) {
//        String userType = gtid.getText();
        String userType = validateLogin(gtid.getText(), pw.getText());
        if (userType.equals(INVALID_TAG)) {
            message.setText("Username or password is invalid. Try again or contact your sysadmin.");
        } else {
            LoginStore.logIn(gtid.getText(), userType);
            transition(event, "menu");
        }
    }

    @Override
    protected void goBack(ActionEvent event) {
        // do nothing - login controller does not use it
    }

    
    public final static String INVALID_TAG = "INVALID";

    public static String validateLogin(String username, String password) {
    	String query = "SELECT UserType FROM Users WHERE GTID='" + username + "' AND Password='" + password + "';";
    	ResultSet rset = DatabaseController.sendQuery(query);

    	try {
    		if(rset.next()) {
        		return rset.getString("UserType");
        	}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
        return INVALID_TAG;
    }

    @Override
    protected void populate(ActionEvent event) {
        // not needed
    }
}
