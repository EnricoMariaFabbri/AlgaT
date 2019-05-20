package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class PaginaIniziale{
private
@FXML
	TextField numberLesson;
@FXML
	Button goToLesson;

	public void goToLesson(ActionEvent e){
		String numLezione=numberLesson.getText();
		boolean flag=GestioneErrori.checkLesson(numLezione);
		if(flag) {
			Scene scene=Navigator.getLesson(Integer.parseInt(numLezione));
			Stage stage =(Stage) goToLesson.getScene().getWindow();
			stage.setScene(scene);
		}
	}
}

