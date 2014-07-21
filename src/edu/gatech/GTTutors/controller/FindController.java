package edu.gatech.GTTutors.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.gatech.GTTutors.main.DatabaseController;
import edu.gatech.GTTutors.main.GTTutorsLaunch;
import edu.gatech.GTTutors.model.FindPOJO;

public class FindController extends AbstractController {
    
    @FXML
    private Label message;
    
    @FXML
    private TextField gtid;
    
    @FXML
    private TableView<FindPOJO> findTable;
    
    @FXML
    private TableColumn<FindPOJO, String> day;
    @FXML
    private TableColumn<FindPOJO, String> time;
    @FXML
    private TableColumn<FindPOJO, String> name;
    @FXML
    private TableColumn<FindPOJO, String> email;
    @FXML
    private TableColumn<FindPOJO, String> course;

    @Override
    protected void submit(ActionEvent event) {
        if(!GTTutorsLaunch.log.getUsername().equals(gtid.getText())) {
            message.setText("You can only see your own schedule.");
            return;
        } else {
            populateResults();
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
    
    private void populateResults() {
        
        day.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("day"));
        time.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("time"));
        name.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("email"));
        course.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("course"));
        
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(DatabaseController.DB_URL + DatabaseController.GROUP,
                                                                DatabaseController.GROUP,
                                                                DatabaseController.PW);
            Statement stmt = connect.createStatement();

            String strSelect = "SELECT Weekday, Time, Name, Email, School, Number"
                    + " FROM TutorScheduleInfo WHERE TGTID=\"" + GTTutorsLaunch.log.getUsername() + "\""
                    + " AND Semester=\"" + GTTutorsLaunch.log.getCurrentSemester() + "\";";
            System.out.println(strSelect);
            
            ResultSet rset = stmt.executeQuery(strSelect);
            // TODO: store the db info into the table
            
            ObservableList<FindPOJO> tableData = FXCollections.observableArrayList();
            while(rset.next()) {
                String day = rset.getString("Weekday");
                String time = rset.getString("Time");
                String name = rset.getString("Name");
                String email = rset.getString("Email");
                String course = rset.getString("School") + " " + rset.getString("Number");
                System.out.println(day);
                tableData.add(new FindPOJO(day, time, name, email, course));
            }
            findTable.setItems(tableData);
            
        } catch (Exception e) {
            e.printStackTrace();
            message.setText("uh-oh, something went wrong.");
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
