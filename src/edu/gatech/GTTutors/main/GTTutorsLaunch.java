package edu.gatech.GTTutors.main;

import edu.gatech.GTTutors.model.LoginStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GTTutorsLaunch extends Application {
    
    public final static int WIDTH = 600;
    public final static int HEIGHT = 500;

    public static final String DB_URL = "jdbc:mysql://academic-mysql.cc.gatech.edu/";
    public static final String GROUP = "cs4400_Group_30";
    public static final String PW = "ArdHSY4u";
    public static final String[] USER_TYPES = {"Tutor", "Student", "Professor", "Administrator"};
    
    public final static String SCREEN_URL = "/screens/";
    public final static String FXML_EXT = ".fxml";
    public static LoginStore log = new LoginStore();

    @Override
    public void start(Stage primaryStage) throws Exception {
        String foo = SCREEN_URL+"login"+FXML_EXT;
        
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
