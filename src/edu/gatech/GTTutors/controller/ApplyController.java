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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import edu.gatech.GTTutors.main.GTTutorsLaunch;
import edu.gatech.GTTutors.model.ApplyPOJO;
import edu.gatech.GTTutors.model.Sum1POJO;

public class ApplyController extends AbstractController {
    
    @FXML
    private Text message;
    
    @FXML
    private CheckBox m9;
    @FXML
    private CheckBox m10;
    @FXML
    private CheckBox m11;
    @FXML
    private CheckBox m12;
    @FXML
    private CheckBox m13;
    @FXML
    private CheckBox m14;
    @FXML
    private CheckBox m15;
    @FXML
    private CheckBox m16;
    @FXML
    private CheckBox[] mondays;
    
    @FXML
    private CheckBox t9;
    @FXML
    private CheckBox t10;
    @FXML
    private CheckBox t11;
    @FXML
    private CheckBox t12;
    @FXML
    private CheckBox t13;
    @FXML
    private CheckBox t14;
    @FXML
    private CheckBox t15;
    @FXML
    private CheckBox t16;
    @FXML
    private CheckBox[] tuesdays;
    
    @FXML
    private CheckBox w9;
    @FXML
    private CheckBox w10;
    @FXML
    private CheckBox w11;
    @FXML
    private CheckBox w12;
    @FXML
    private CheckBox w13;
    @FXML
    private CheckBox w14;
    @FXML
    private CheckBox w15;
    @FXML
    private CheckBox w16;
    @FXML
    private CheckBox[] wednesdays;
    
    @FXML
    private CheckBox r9;
    @FXML
    private CheckBox r10;
    @FXML
    private CheckBox r11;
    @FXML
    private CheckBox r12;
    @FXML
    private CheckBox r13;
    @FXML
    private CheckBox r14;
    @FXML
    private CheckBox r15;
    @FXML
    private CheckBox r16;
    @FXML
    private CheckBox[] thursdays;
    
    @FXML
    private CheckBox f9;
    @FXML
    private CheckBox f10;
    @FXML
    private CheckBox f11;
    @FXML
    private CheckBox f12;
    @FXML
    private CheckBox f13;
    @FXML
    private CheckBox f14;
    @FXML
    private CheckBox f15;
    @FXML
    private CheckBox f16;
    @FXML
    private CheckBox[] fridays;

    @FXML
    private TextField gpa;
    @FXML
    private TextField phone;
    
    @FXML
    private TableView<ApplyPOJO> applyTable;
    @FXML
    private TableColumn<ApplyPOJO, String> school;
    @FXML
    private TableColumn<ApplyPOJO, String> number;
    @FXML
    private TableColumn<ApplyPOJO, CheckBox> gta;
    @FXML
    private TableColumn<ApplyPOJO, CheckBox> select;
    
    @FXML
    public void initialize() {
        mondays = new CheckBox[]{m9, m10, m11, m12, m13, m14, m15, m16};
        tuesdays = new CheckBox[]{t9, t10, t11, t12, t13, t14, t15, t16};
        wednesdays = new CheckBox[]{w9, w10, w11, w12, w13, w14, w15, w16};
        thursdays = new CheckBox[]{r9, r10, r11, r12, r13, r14, r15, r16};
        fridays = new CheckBox[]{f9, f10, f11, f12, f13, f14, f15, f16};
    }

    @Override
    protected void submit(ActionEvent event) {
        Set<String> times = new LinkedHashSet<>();
        
        for(CheckBox box : mondays) {
            if(box.isSelected()) {
                times.add("Monday "+parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : tuesdays) {
            if(box.isSelected()) {
                times.add("Tuesday "+parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : wednesdays) {
            if(box.isSelected()) {
                times.add("Wednesday "+parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : thursdays) {
            if(box.isSelected()) {
                times.add("Thursday "+parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : fridays) {
            if(box.isSelected()) {
                times.add("Friday "+parseTime(box.getId().substring(1)));
            }
        }
        
        submitResults(times);
    }
    
    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        school.setCellValueFactory(new PropertyValueFactory<ApplyPOJO,String>("school"));
        number.setCellValueFactory(new PropertyValueFactory<ApplyPOJO,String>("number"));
        gta.setCellValueFactory(new PropertyValueFactory<ApplyPOJO,CheckBox>("gta"));
        select.setCellValueFactory(new PropertyValueFactory<ApplyPOJO,CheckBox>("select"));
        
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String query = "SELECT * FROM Course;";
            System.out.println(query);
            ResultSet rset = stmt.executeQuery(query);
            
            ObservableList<ApplyPOJO> rows = FXCollections.observableArrayList();
            while(rset.next()) {
                rows.add(new ApplyPOJO(rset.getString("School"), rset.getString("Number"), new CheckBox(), new CheckBox()));               
            }
            applyTable.setItems(rows);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connect != null)
                    connect.close();
            } catch(SQLException e) {}
        }
    }
    
    private void submitResults(Set<String> times) {
        // TODO: implement to submit
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.GROUP,
                                                                GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String query = "SELECT TGTID FROM Recommends WHERE TGTID=\""+GTTutorsLaunch.log.getUsername()+"\";";
            System.out.println(query);
            
            ResultSet rset = stmt.executeQuery(query);
            if(!rset.next()) {
                message.setText("You must have at least 1 recommendation from a professor!");
                return;
            }
            
            // inserting tutor information
            query = "INSERT IGNORE INTO Tutor VALUES (\""+GTTutorsLaunch.log.getUsername()+"\","
                    +"\""+phone.getText()+"\","
                    +gpa.getText()
                    +");";
            System.out.println(query);
            //stmt.executeUpdate(query);

            
            // iterating through courses
            ObservableList<ApplyPOJO> courses = applyTable.getItems();
            
            query = "SELECT GTID FROM Graduate WHERE GTID=\""+GTTutorsLaunch.log.getUsername()+"\";";
            System.out.println(query);
            rset = stmt.executeQuery(query);
            boolean isGrad = rset.next();
            
            for(ApplyPOJO course : courses) {
                if(course.getGta().isSelected() && !isGrad) {
                    message.setText("You must be a graduate in order to be a graduate TA!");
                } else {
                    int isGta = course.getGta().isSelected() ? 1 : 0;
                    query = "INSERT IGNORE INTO Tutors VALUES (\""+GTTutorsLaunch.log.getUsername()+"\","
                            +"\""+course.getSchool()+"\","
                            +"\""+course.getNumber()+"\","
                            +isGta
                            +");";
                    System.out.println(query);
                    //stmt.executeUpdate(query);
                }
            }
            
            // iterating through times available
            for(String time : times) {
                String[] timeSplit = time.split(" ");
                query = "INSERT IGNORE INTO TimeSlot VALUES (\""+GTTutorsLaunch.log.getUsername()+"\","
                        +"\""+timeSplit[1]+"\","
                        +"\""+GTTutorsLaunch.log.getCurrentSemester()+"\","
                        +"\""+timeSplit[0]+"\""
                        +");";
                System.out.println(query);
                //stmt.executeUpdate(query);
            }
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
