package edu.gatech.GTTutors.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        populateResults();
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
        // TODO: implement
        course.setCellValueFactory(new PropertyValueFactory<Sum1POJO,String>("course"));
        semester.setCellValueFactory(new PropertyValueFactory<Sum1POJO,String>("semester"));
        numStudents.setCellValueFactory(new PropertyValueFactory<Sum1POJO,Integer>("numStudents"));
        numTutors.setCellValueFactory(new PropertyValueFactory<Sum1POJO,Integer>("numTutors"));
        
        // put database manipulation here
    }

}
