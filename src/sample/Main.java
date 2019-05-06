package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
public
	void start(Stage stage) {
	stage.setTitle("AlgaT");
	stage.show();
	/*Controller contr=new Controller(stage);
	contr.goStartPage();*/
	new Test().findPath();
	}

public static void main(String[] args) {
	    launch(args);
}
}



