package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PaginaIniziale{
private
@FXML
	TextField numberLesson;
@FXML
	Button goToLesson;

	public void goToLesson(ActionEvent e){
		String numLezione=numberLesson.getText();
		int maxsize=Navigator.getLessonsSize();
		if(GestioneErrori.controlLessonIndex(numLezione,maxsize)) {//se esiste la lezione i-esima ed è valida
			Scene scene=Navigator.getLesson(Integer.parseInt(numLezione));//carico la lezione i-esima
			Stage stage =(Stage) goToLesson.getScene().getWindow();
			stage.setScene(scene);
		}
	}
}

