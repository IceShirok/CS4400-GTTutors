package src.code.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RecommendController extends AbstractController {
    
    @FXML
    private TextField studentGtid;
    @FXML
    private TextArea description;
    @FXML
    private ToggleGroup ratings;

    @Override
    protected void submit(ActionEvent event) {
        // TODO: implement submission into DB
        System.out.println(studentGtid.getText());
        System.out.println(description.getText());
        System.out.println(((RadioButton)ratings.getSelectedToggle()).getId());
        // also enforce that ratings must be filled out - no null
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        // do not implement - not needed
    }

}
