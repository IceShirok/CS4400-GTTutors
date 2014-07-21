package edu.gatech.GTTutors.controller;

import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.gatech.GTTutors.main.GTTutorsLaunch;

public abstract class AbstractController {
    
    public final static String SCREEN_URL = "/screens/";
    public final static String FXML_EXT = ".fxml";
    
    @FXML
    protected abstract void submit(ActionEvent event);
    
    @FXML
    protected abstract void goBack(ActionEvent event);
    
    @FXML
    protected void transition(ActionEvent event, String filename) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SCREEN_URL+filename+FXML_EXT));
            Parent root = (Parent) fxmlLoader.load();

            AbstractController controller = fxmlLoader.getController();
            fxmlLoader.setController(controller);
            controller.populate(event);

            showScene(event, new Scene(root, GTTutorsLaunch.WIDTH, GTTutorsLaunch.HEIGHT));

        } catch (Exception e) {
        	e.printStackTrace();
//            System.out.println("Something went wrong with loading the scene...: " + e);
        }
    }
    
    @FXML
    protected abstract void populate(ActionEvent event);
    
    public void showScene(ActionEvent event, Scene scene) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    protected String parseTime(String stime) {
        int time = Integer.parseInt(stime);
        if(time < 12) {
            return "\""+time+"AM"+"\"";
        } else if(time == 12) {
            return "\""+time+"PM"+"\"";
        } else {
            return "\""+(time-12)+"PM"+"\"";
        }
    }
    
    protected String processInClause(Set<String> set) {
        String aString = "(";
        for(String item : set) {
            aString += item + ",";
        }
        aString = aString.substring(0, aString.length()-1);
        aString += ")";
        return aString;
    }

}
