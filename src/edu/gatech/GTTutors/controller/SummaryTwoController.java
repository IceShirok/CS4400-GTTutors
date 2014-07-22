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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.gatech.GTTutors.main.DatabaseController;
import edu.gatech.GTTutors.model.Sum1POJO;
import edu.gatech.GTTutors.model.Sum2POJO;

public class SummaryTwoController extends AbstractController {
    
    @FXML
    private Label message;
    
    @FXML
    private CheckBox fall;
    @FXML
    private CheckBox spring;
    @FXML
    private CheckBox summer;
    
    @FXML
    private TableView<Sum2POJO> sum2Table;
    
    @FXML
    private TableColumn<Sum2POJO, String> course;
    @FXML
    private TableColumn<Sum2POJO, String> semester;
    @FXML
    private TableColumn<Sum2POJO, Integer> numTa;
    @FXML
    private TableColumn<Sum2POJO, Double> avgTa;
    @FXML
    private TableColumn<Sum2POJO, Integer> numNot;
    @FXML
    private TableColumn<Sum2POJO, Double> avgNot;

    @Override
    protected void submit(ActionEvent event) {
        Set<String> semestersSet = new LinkedHashSet<>();
        if(fall.isSelected()) {
            semestersSet.add("Fall");
        }
        if(spring.isSelected()) {
            semestersSet.add("Spring");
        }
        if(summer.isSelected()) {
            semestersSet.add("Summer");
        }
        String semesters = processInClause(semestersSet);
        populateResults(semesters);
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        // do not implement - not needed
    }
    
    private void populateResults(String semesters) {
        // TODO: implement
        course.setCellValueFactory(new PropertyValueFactory<Sum2POJO,String>("course"));
        semester.setCellValueFactory(new PropertyValueFactory<Sum2POJO,String>("semester"));
        numTa.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Integer>("numTa"));
        avgTa.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Double>("avgTa"));
        numNot.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Integer>("numNot"));
        avgNot.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Double>("avgNot"));
        
        String query1 = "select R.School, R.Number, R.Semester, count(T.GTA) as nonTA, avg(R.Rating) as avgRating" +
        				" from Rates R left outer join Tutors T on T.GTID=R.TGTID and T.School=R.School and T.Number=R.Number" +
        				" where T.GTA=0" +
        				" group by R.School, R.Number, R.Semester" +
        				" order by R.School ASC";
        String query2 = "select R.School, R.Number, R.Semester, count(T.GTA) as TA, avg(R.Rating) as avgRating" +
						" from Rates R left outer join Tutors T on T.GTID=R.TGTID and T.School=R.School and T.Number=R.Number" +
						" where T.GTA=1" +
						" group by R.School, R.Number, R.Semester" +
						" order by R.School ASC";

        Connection connect = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(DatabaseController.DB_URL + DatabaseController.GROUP,
                                                                DatabaseController.GROUP,
                                                                DatabaseController.PW);            
            Statement stmt1 = connect.createStatement();
            Statement stmt2 = connect.createStatement();
            ResultSet rset1 = stmt1.executeQuery(query1);
            ResultSet rset2 = stmt2.executeQuery(query2);
            
            ObservableList<Sum2POJO> rows = FXCollections.observableArrayList();
            String last = "";
            while(rset1.next()) {
            	String course = rset1.getString("School") + rset1.getString("Number");
            	if(course.equals(last)) {
            		course = "";
            	} else {
            		last = course;
            		if(rows.size() > 0) {
            			rows.add(new Sum2POJO("", "Avg", 0, 0, 0, 0));
            		}
            	}
            	String semester = rset1.getString("Semester");
            	int nta = rset1.getInt("nonTA");
            	double avg = rset1.getDouble("avgRating");
            	rows.add(new Sum2POJO(course, semester, 0, 0, nta, avg));
            }
            rows.add(new Sum2POJO("", "Avg", 0, 0, 0, 0));
            while(rset2.next()) {
            	String course = rset2.getString("School") + rset2.getString("Number");
            	String semester = rset2.getString("Semester");
            	int ta = rset2.getInt("TA");
            	double avg = rset2.getDouble("avgRating");
            	for(Sum2POJO p : rows) {
            		if(course.equals(p.getCourse()) && semester.equals(p.getSemester())) {
            			p.setNumTa(ta);
            			p.setAvgTA(avg);
            		}
            	}
            }
            double ntaSumAvg = 0, taSumAvg = 0, taCount = 0, ntaCount = 0;
            for(Sum2POJO p : rows) {
            	if(p.getSemester().equals("Avg")) {
            		p.setAvgNot(ntaSumAvg / ntaCount);
            		p.setAvgTA(taSumAvg / taCount);
            		ntaCount = taCount = ntaSumAvg = taSumAvg = 0;
            	} else {
            		ntaSumAvg += p.getAvgNot();
            		taSumAvg += p.getAvgTa();
            		ntaCount += p.getNumNot();
            		taCount += p.getNumTa();
            	}
            }
           sum2Table.setItems(rows);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(connect!=null) {
                    connect.close();
                }
            } catch (Exception e) {
                message.setText(e.getMessage() + " Please contact the database admin for help.");
            }
        }
        
    }

}
