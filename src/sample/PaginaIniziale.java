package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PaginaIniziale extends GridPane {
private
	TextField numberLesson;
	Button goToLesson;
	Controller c;
public
	PaginaIniziale(Controller contr) {
	this.c=contr;
	this.setAlignment(Pos.TOP_LEFT);
	this.setHgap(10);
	this.setVgap(10);
	this.numberLesson=new TextField();
	this.add(numberLesson,0,1);
	this.goToLesson=new Button("VAI ALLA LEZIONE");
	this.add(goToLesson,1,1);
	
	this.goToLesson.setOnAction(new EventHandler<ActionEvent>(){
		public void handle(ActionEvent e){
			Integer lesson=Integer.parseInt(numberLesson.getText());
			c.changeScene(c.lessons.get(lesson));
		}
	});
	
	}
}

