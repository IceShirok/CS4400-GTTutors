package edu.gatech.GTTutors.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import edu.gatech.GTTutors.main.GTTutorsLaunch;

public class RecommendController extends AbstractController {
    
    @FXML
    private Text message;
    
    @FXML
    private TextField studentGtid;
    @FXML
    private TextArea description;
    @FXML
    private ToggleGroup ratings;

    @Override
    protected void submit(ActionEvent event) {
        String tutorGtid = studentGtid.getText();
        String desc = description.getText();
        RadioButton selected = (RadioButton)ratings.getSelectedToggle();
        if(tutorGtid == null || desc == null || selected == null
                || tutorGtid.length() == 0 || desc.length() == 0) {
            message.setText("Please fill out all entries.");
        } else {
            message.setText("");
            int rating = Integer.parseInt(selected.getId().substring(4));
            submitRecommendForm(tutorGtid, desc, rating);
        }
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        // do not implement - not needed
    }
    
    private void submitRecommendForm(String name, String description, int rating) {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String strSelect = "INSERT INTO Recommends VALUES(\""+ GTTutorsLaunch.log.getUsername() + "\", \""
                                                                + name + "\", \""
                                                                + description + "\", "
                                                                + rating + ");";
            System.out.println(strSelect);
            stmt.executeUpdate(strSelect);
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("GTID is not valid or you have already recommended this student.");
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
