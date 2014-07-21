package edu.gatech.GTTutors.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.gatech.GTTutors.main.DatabaseController;
import edu.gatech.GTTutors.model.Sum1POJO;

public class SummaryOneController extends AbstractController {

    @FXML
    private Label message;
    
    @FXML
    private CheckBox fall;
    @FXML
    private CheckBox spring;
    @FXML
    private CheckBox summer;
    
    @FXML
    private TableView<Sum1POJO> sum1Table;
    
    @FXML
    private TableColumn<Sum1POJO, String> course;
    @FXML
    private TableColumn<Sum1POJO, String> semester;
    @FXML
    private TableColumn<Sum1POJO, Integer> numStudents;
    @FXML
    private TableColumn<Sum1POJO, Integer> numTutors;
    
    @Override
    protected void submit(ActionEvent event) {
        Set<String> semestersSet = new LinkedHashSet<>();
        if(fall.isSelected()) {
            semestersSet.add("\"Fall\"");
        }
        if(spring.isSelected()) {
            semestersSet.add("\"Spring\"");
        }
        if(summer.isSelected()) {
            semestersSet.add("\"Summer\"");
        }
        
        if(semestersSet.isEmpty()) {
            message.setText("Didn't select a semester!");
        } else {
            String semesters = processInClause(semestersSet);
            populateResults(semesters);
        }
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }
    
    @Override
    protected void populate(ActionEvent event) {
        
    }
    
    private void populateResults(String semesters) {
        // TODO: implement
        course.setCellValueFactory(new PropertyValueFactory<Sum1POJO,String>("course"));
        semester.setCellValueFactory(new PropertyValueFactory<Sum1POJO,String>("semester"));
        numStudents.setCellValueFactory(new PropertyValueFactory<Sum1POJO,Integer>("numStudents"));
        numTutors.setCellValueFactory(new PropertyValueFactory<Sum1POJO,Integer>("numTutors"));
        
        String query = "select H.School, H.Number, H.Semester, count(DISTINCT H.GTID) as SGTID, count(DISTINCT S.GTID) as TGTID" +
 			   " from Hires H left outer join TimeSlot S on S.Time=H.Time and S.Weekday=H.Weekday and S.Semester=H.Semester" +
 			   " where H.Semester IN " + semesters +
 			   " group by H.School, H.Number, H.Semester" +
 			   " order by H.School ASC";
        System.out.println(query);
        
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(DatabaseController.DB_URL + DatabaseController.GROUP,
                                                                DatabaseController.GROUP,
                                                                DatabaseController.PW);
            Statement stmt = connect.createStatement();
            ResultSet rset = stmt.executeQuery(query);
            
            ObservableList<Sum1POJO> rows = FXCollections.observableArrayList();
            while(rset.next()) {
            	String course = rset.getString("School") + rset.getString("Number");
            	String semester = rset.getString("Semester");
            	int numStudents = rset.getInt("SGTID");
            	int numTutors = rset.getInt("TGTID");
            	rows.add(new Sum1POJO(course, semester, numStudents, numTutors));            	
            }
            sum1Table.setItems(rows);
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
	        	if(connect != null)
	        		connect.close();
        	} catch(SQLException e) {}
        }
    }

}
