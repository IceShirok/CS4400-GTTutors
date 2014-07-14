package edu.gatech.GTTutors.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import edu.gatech.GTTutors.model.Sum2POJO;

public class SummaryTwoController extends AbstractController {
    
    @FXML
    private Text message;
    
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
        course.setCellValueFactory(new PropertyValueFactory<Sum2POJO,String>("course"));
        semester.setCellValueFactory(new PropertyValueFactory<Sum2POJO,String>("semester"));
        numTa.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Integer>("numTa"));
        avgTa.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Double>("avgTa"));
        numNot.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Integer>("numNot"));
        avgNot.setCellValueFactory(new PropertyValueFactory<Sum2POJO,Double>("avgNot"));
        
        // TODO: make this more efficient?
        // don't think you can get around copy+paste this kind of stuff...
        /*
        Connection connect = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("");
            
            Statement stmt = connect.createStatement();
            
            stmt.executeUpdate("DROP VIEW IF EXISTS CurrentYear");
            stmt.executeUpdate("DROP VIEW IF EXISTS PreviousYear");
            stmt.executeUpdate("DROP VIEW IF EXISTS RetroactiveYears");
            stmt.executeUpdate("DROP VIEW IF EXISTS ProactiveYears");
            stmt.executeUpdate("DROP VIEW IF EXISTS SgaAllocationResults");
            //executeUpdate = update DB
            //executeQuery = get some results back
            
            ResultSet rset = stmt.executeQuery("SELECT * FROM CurrentYear;");
            
            
            ObservableList<Sum2POJO> tableData = FXCollections.observableArrayList();
            while(rset.next()) {
                String semester = rset.getString("Semester");
                //so on, so forth
                //calculating the averages and stuff goes here too
                tableData.add(new Sum2POJO("", semester, 0, 0, 0, 0));
            }
            sum2Table.setItems(tableData);
        } catch (Exception e) {
            System.out.println(e);
            message.setText(e.getMessage() + " Please contact the database admin for help.");
        } finally {
            try {
                if(connect!=null) {
                    connect.close();
                }
            } catch (Exception e) {
                message.setText(e.getMessage() + " Please contact the database admin for help.");
            }
        }
        */
    }

}
