package edu.gatech.GTTutors.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import edu.gatech.GTTutors.main.GTTutorsLaunch;

public class LoginController extends AbstractController {
    
    @FXML
    private Text message;

    @FXML
    private TextField gtid;

    @FXML
    private PasswordField pw;

    @FXML
    protected void submit(ActionEvent event) {
        String userType = validateLogin(gtid.getText(), pw.getText());
        if (userType.equals("INVALID")) {
            message.setText("Username or password is invalid. Try again or contact your sysadmin.");
        } else {
            GTTutorsLaunch.log.logIn(gtid.getText(), userType);
            System.out.println(GTTutorsLaunch.log.getUsername());
            transition(event, "menu");
        }
    }

    private String validateLogin(String username, String password) {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String strSelect = "SELECT * FROM User WHERE GTID='" + username + "' AND Password='" + password + "';";
            System.out.println(strSelect);
            ResultSet rset = stmt.executeQuery(strSelect);
            
            if (rset.next()) {                
                for(String type : GTTutorsLaunch.USER_TYPES) {
                    strSelect = "SELECT * FROM " + type + " WHERE GTID='" + username + "';";
                    System.out.println(strSelect);
                    rset = stmt.executeQuery(strSelect);
                    if(rset.next()) {
                        return type;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "INVALID";
    }

    @Override
    protected void goBack(ActionEvent event) {
        // do nothing - login controller does not use it
    }

    @Override
    protected void populate(ActionEvent event) {
        // not needed
    }
}
