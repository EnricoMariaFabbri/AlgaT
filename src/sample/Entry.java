package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class Entry extends Application {
    public void start(Stage primaryStage)throws Exception{
        Navigator nav=new Navigator();
        GridPane paginaIniziale=FXMLLoader.load(getClass().getResource("PaginaIniziale.fxml"));
        primaryStage.setTitle("AlgaT");
        primaryStage.setScene(new Scene(paginaIniziale,2000,1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
