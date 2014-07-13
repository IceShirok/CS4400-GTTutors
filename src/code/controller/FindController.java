package code.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import code.model.FindPOJO;

public class FindController extends AbstractController {
    
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
        System.out.println(gtid.getText());
        // TODO: implement
        day.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("day"));
        time.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("time"));
        name.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("email"));
        course.setCellValueFactory(new PropertyValueFactory<FindPOJO,String>("course"));
        
        // put database manipulation here
    }

}
