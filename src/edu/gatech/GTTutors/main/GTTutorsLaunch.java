package edu.gatech.GTTutors.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GTTutorsLaunch extends Application {
    
    public final static int WIDTH = 600;
    public final static int HEIGHT = 500;
    
    public final static String SCREEN_URL = "/screens/";
    public final static String FXML_EXT = ".fxml";
    public DatabaseController db;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String foo = SCREEN_URL+"login"+FXML_EXT;
        
        db = new DatabaseController();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(foo));
        Parent root = (Parent) fxmlLoader.load();
        Scene mainScreen = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Georgia Tech Tutors - Made by students!");
        primaryStage.setScene(mainScreen);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
