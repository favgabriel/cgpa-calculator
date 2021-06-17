package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Boolean isSplashedLoaded=false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Image img= new Image ("icon.png");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CGPA CALCULATOR");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets ().add ("css.css");
        primaryStage.setResizable (false);
        primaryStage.getIcons ().add (img);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
