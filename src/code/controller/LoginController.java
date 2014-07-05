package src.code.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import src.code.model.LoginStore;

public class LoginController extends AbstractController {

    @FXML
    private Text message;

    @FXML
    private TextField gtid;

    @FXML
    private PasswordField pw;

    @FXML
    protected void submit(ActionEvent event) {
        String userType = gtid.getText();
        //String userType = validateLogin(gtid.getText(), pw.getText());
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

    // cs4400_Group_30 - ArdHSY4u
    public final static String DB_URL = "localhost:5775/campania_status";
    public final static String INVALID_TAG = "INVALID";

    public static String validateLogin(String username, String password) {
        // TODO: hook DB up to correct connection
        // then test to make sure everything works
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://" + DB_URL);

            Statement stmt = connect.createStatement();
            String strSelect = "SELECT UserType FROM Users WHERE GTID='"
                    + username + "' AND Password='" + password + "';";
            ResultSet rset = stmt.executeQuery(strSelect);
            if (rset.next()) {
                return rset.getString("UserType");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return INVALID_TAG;
    }

    @Override
    protected void populate(ActionEvent event) {
        // not needed
    }
}
