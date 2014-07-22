package edu.gatech.GTTutors.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import edu.gatech.GTTutors.main.GTTutorsLaunch;
import edu.gatech.GTTutors.model.SearchPOJO;

public class SearchController extends AbstractController {
    
    @FXML
    private Text message;
    
    @FXML
    private ChoiceBox<String> courses;
    
    @FXML
    private TableView<SearchPOJO> searchTable;
    
    @FXML
    private TableColumn<SearchPOJO, String> name;
    @FXML
    private TableColumn<SearchPOJO, String> email;
    @FXML
    private TableColumn<SearchPOJO, String> day;
    @FXML
    private TableColumn<SearchPOJO, String> time;
    @FXML
    private TableColumn<SearchPOJO, Double> avgProf;
    @FXML
    private TableColumn<SearchPOJO, Integer> numProf;
    @FXML
    private TableColumn<SearchPOJO, Double> avgStudent;
    @FXML
    private TableColumn<SearchPOJO, Integer> numStudent;
    
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
    public void initialize() {
        mondays = new CheckBox[]{m9, m10, m11, m12, m13, m14, m15, m16};
        tuesdays = new CheckBox[]{t9, t10, t11, t12, t13, t14, t15, t16};
        wednesdays = new CheckBox[]{w9, w10, w11, w12, w13, w14, w15, w16};
        thursdays = new CheckBox[]{r9, r10, r11, r12, r13, r14, r15, r16};
        fridays = new CheckBox[]{f9, f10, f11, f12, f13, f14, f15, f16};
    }

    @Override
    protected void submit(ActionEvent event) {
        if(courses.getValue() == null) {
            message.setText("Please fill out all entries.");
            return;
        }
        String[] courseSplit = courses.getValue().split(" ");
        
        Set<String> timesSet = new LinkedHashSet<>();
        Set<String> daysSet = new LinkedHashSet<>();
        
        for(CheckBox box : mondays) {
            if(box.isSelected()) {
                daysSet.add("\"Monday\"");
                timesSet.add(parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : tuesdays) {
            if(box.isSelected()) {
                daysSet.add("\"Tuesday\"");
                timesSet.add(parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : wednesdays) {
            if(box.isSelected()) {
                daysSet.add("\"Wednesday\"");
                timesSet.add(parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : thursdays) {
            if(box.isSelected()) {
                daysSet.add("\"Thursday\"");
                timesSet.add(parseTime(box.getId().substring(1)));
            }
        }
        for(CheckBox box : fridays) {
            if(box.isSelected()) {
                daysSet.add("\"Friday\"");
                timesSet.add(parseTime(box.getId().substring(1)));
            }
        }

        if(daysSet.isEmpty()) {
            message.setText("Please fill out all entries.");
            return;
        }
        
        message.setText("");
        String days = processInClause(daysSet);
        String times = processInClause(timesSet);
        
        populateResults(courseSplit[0], courseSplit[1], times, days);
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
            
            String strSelect = "SELECT DISTINCT School, Number FROM AvailableTimeSlots NATURAL JOIN Tutors"
                    + " WHERE Semester=\"" + GTTutorsLaunch.log.getCurrentSemester() + "\";";
            System.out.println(strSelect);
            ResultSet rset = stmt.executeQuery(strSelect);

            ObservableList<String> row = FXCollections.observableArrayList();
            while(rset.next()) {
                row.add(rset.getString("School") + " " + rset.getString("Number"));
            }
            courses.setItems(row);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void populateResults(String school, String number, String times, String days) {
        name.setCellValueFactory(new PropertyValueFactory<SearchPOJO,String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<SearchPOJO,String>("email"));
        day.setCellValueFactory(new PropertyValueFactory<SearchPOJO,String>("day"));
        time.setCellValueFactory(new PropertyValueFactory<SearchPOJO,String>("time"));
        avgProf.setCellValueFactory(new PropertyValueFactory<SearchPOJO,Double>("avgProf"));
        numProf.setCellValueFactory(new PropertyValueFactory<SearchPOJO,Integer>("numProf"));
        avgStudent.setCellValueFactory(new PropertyValueFactory<SearchPOJO,Double>("avgStudent"));
        numStudent.setCellValueFactory(new PropertyValueFactory<SearchPOJO,Integer>("numStudent"));
        
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                    GTTutorsLaunch.GROUP,
                    GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String strSelect = "SELECT T.Name, T.Email, T.AvgProf, T.NumProf, T.AvgProf, T.NumProf, A.Weekday, A.Time"
                + " FROM TutorRatings T NATURAL JOIN AvailableTimeSlots A NATURAL JOIN Tutors"
                + " WHERE Semester=\"" + GTTutorsLaunch.log.getCurrentSemester() + "\""
                    + " AND School=\"" + school + "\""
                    + " AND Number=\"" + number + "\""
                    + " AND Weekday IN " + days
                    + " AND Time IN " + times
                + " ORDER BY T.AvgStudent ASC, T.GTID ASC;";
            System.out.println(strSelect);
            ResultSet rset = stmt.executeQuery(strSelect);

            ObservableList<SearchPOJO> row = FXCollections.observableArrayList();
            while(rset.next()) {
                String name = (rset.getString("Name"));
                String email = (rset.getString("Email"));
                String day = (rset.getString("Weekday"));
                String time = (rset.getString("Time"));
                double avgProf = (rset.getDouble("AvgProf"));
                int numProf = (rset.getInt("NumProf"));
                double avgStudent = (rset.getDouble("AvgProf"));
                int numStudent = (rset.getInt("NumProf"));
                row.add(new SearchPOJO(name, email, day, time, avgProf, numProf, avgStudent, numStudent));
            }
            searchTable.setItems(row);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    protected void schedule() {
        System.out.println("I'm scheduling!");
        SearchPOJO selectedTutor = searchTable.getSelectionModel().getSelectedItem();
        if(selectedTutor == null) {
            message.setText("Please select a tutor first.");
            return;
        }
        
        String[] courseSplit = courses.getValue().split(" ");
        
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(GTTutorsLaunch.DB_URL + GTTutorsLaunch.GROUP,
                    GTTutorsLaunch.GROUP,
                    GTTutorsLaunch.PW);
            Statement stmt = connect.createStatement();
            
            String strSelect = "INSERT INTO Hires VALUES ("
                    + "\"" + GTTutorsLaunch.log.getUsername() + "\","
                    + "\"" + courseSplit[0] + "\","
                    + "\"" + courseSplit[1] + "\","
                    + "\"" + selectedTutor.getTime() + "\","
                    + "\"" + GTTutorsLaunch.log.getCurrentSemester() + "\","
                    + "\"" + selectedTutor.getDay() + "\""
                    + ");";
            System.out.println(strSelect);
            stmt.executeUpdate(strSelect);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
