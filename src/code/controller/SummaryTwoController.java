package src.code.controller;

import javafx.event.ActionEvent;

public class SummaryTwoController extends AbstractController {

    @Override
    protected void submit(ActionEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        // TODO Auto-generated method stub
        
    }

}
