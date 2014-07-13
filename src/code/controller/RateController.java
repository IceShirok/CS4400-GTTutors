package code.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RateController extends AbstractController {

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
        System.out.println(name.getText());
        System.out.println(courses.getValue());
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
        // TODO: implement for course dropdown
    }

}
