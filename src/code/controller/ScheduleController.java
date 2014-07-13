package code.controller;

import javafx.event.ActionEvent;

public class ScheduleController extends AbstractController {

    @Override
    protected void submit(ActionEvent event) {
        // TODO: submission to DB
    }

    @Override
    protected void goBack(ActionEvent event) {
        transition(event, "menu");
    }

    @Override
    protected void populate(ActionEvent event) {
        // TODO: populate with data from search controller
    }

}
