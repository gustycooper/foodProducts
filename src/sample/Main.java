package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  Main class
 */
public class Main extends Application {

    public static ProductDB pdb = new ProductDB("");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Food Be Us");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        // Save file - add code here
        pdb.writeProductDB();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
