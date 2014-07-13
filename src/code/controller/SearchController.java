package code.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class SearchController extends AbstractController {
    
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
        // TODO: display stuff on table
        for(CheckBox box : mondays) {
            if(box.isSelected()) {
                System.out.println(box.getText());
            }
        }
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        // TODO: populate with courses that have available tutors
    }

}
