package sample;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lezione extends Aggregato {
private
	Button goToQuestions;
	Scene relatedQuestions;
public
	Lezione(Controller controller,String title){
		super(controller,title);
		this.goToQuestions=new Button("VAI ALLE DOMANDE");
		this.add(goToQuestions,0,6);
	
		goToQuestions.setOnAction(new EventHandler<ActionEvent>() {		
			public void handle(ActionEvent e) {
				c.changeScene(c.questions.get(getRelatedQuestions()));
			}
		});
	}
	
	void setRelatedQuestions(Scene questions){this.relatedQuestions=questions;}
	
	Integer getRelatedQuestions(){
		char index=this.getTitle().charAt(this.getTitle().length()-1);
		return Character.getNumericValue(index);
	}
}
