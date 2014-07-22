package edu.gatech.GTTutors.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import edu.gatech.GTTutors.main.GTTutorsLaunch;

public class RateController extends AbstractController {
    
    @FXML
    private Label message;

    @FXML
    private ChoiceBox<String> courses;
    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private ToggleGroup ratings;

    @Override
    protected void submit(ActionEvent event) {
        // TODO: implement submission into DB
        String tutorName = name.getText();
        String course = courses.getValue();
        String desc = description.getText();
        RadioButton selected = ((RadioButton)ratings.getSelectedToggle());
        if(tutorName == null || course == null || desc == null || selected == null
                || tutorName.length() == 0 || desc.length() == 0) {
            message.setText("Please fill out all of the form.");
        } else {
            // TODO: parse school and number
            String[] courseSplit = course.split(" ");
            int rating = Integer.parseInt(selected.getId().substring(4));
            submitRatesForm(tutorName, courseSplit[0], courseSplit[1], GTTutorsLaunch.log.getCurrentSemester(), desc, rating);
        }
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String strSelect = "SELECT School, Number FROM Hires"
                                + " WHERE GTID=\"" + GTTutorsLaunch.log.getUsername() + "\""
                                + " AND Semester=\"" + GTTutorsLaunch.log.getCurrentSemester() + "\";";
            System.out.println(strSelect);
            ResultSet rset = stmt.executeQuery(strSelect);

            ObservableList<String> row = FXCollections.observableArrayList();
            while(rset.next()) {
                row.add(rset.getString("School") + " " + rset.getString("Number"));
            }
            courses.setItems(row);
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
    
    private void submitRatesForm(String tutorName,
                                String school, String number, String semester,
                                String desc, int rating) {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            // TODO: check if tutor is valid with student (probably need a view for this)
            String strSelect = "SELECT GTID from Tutor WHERE Name=\"" + tutorName + "\";";
            // need to look up tutor gtid
            String tutorGtid = tutorName;
            
            strSelect = "INSERT INTO Rates VALUES(\""+ GTTutorsLaunch.log.getUsername() + "\", \""
                                                                + tutorGtid + "\", \""
                                                                + school + "\", \""
                                                                + number + "\", \""
                                                                + semester + "\", \""
                                                                + desc + "\", "
                                                                + rating + ");";
            System.out.println(strSelect);
            stmt.executeUpdate(strSelect);
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("Tutor name invalid. Incorrect spelling or wrong tutor?");
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
