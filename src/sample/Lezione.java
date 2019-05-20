package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Lezione {
private
	@FXML
	GridPane currentItem;
	@FXML
	Label title;
	@FXML
	Button nextItem;
	@FXML
	Button prevItem;
	@FXML
	Button startPage;
	@FXML
	Button questions;
	ArrayList<GridPane> items;
	int i;//semplice contatore
	Scene relatedQuestions;

	public Lezione(){
		this.items=new ArrayList<GridPane>();
		this.i=0;
	}

	public void prev(ActionEvent e){
		this.i--;
		GridPane root=(GridPane)nextItem.getParent();
		root.getChildren().remove(currentItem);
		System.out.println(this.i);
		this.currentItem=this.items.get(this.i);
		root.add(this.currentItem,0,1);
	}

	public void next(ActionEvent e){
		GridPane root=(GridPane)nextItem.getParent();
		root.getChildren().remove(currentItem);
		System.out.println(this.i);
		this.currentItem=this.items.get(this.i);
		root.add(this.currentItem,0,1);
		if(this.i==this.items.size()-1){
			//non incrementarlo
		}else {
			this.i++;
		}
	}

	public void goToStartPage(ActionEvent e){
		try {
			GridPane paginaIniziale = FXMLLoader.load(getClass().getResource("PaginaIniziale.fxml"));
			Stage stage=(Stage)startPage.getScene().getWindow();
			stage.setScene(new Scene(paginaIniziale,2000,1000));
		}catch(Exception exc){exc.printStackTrace();}
	}
	public void goToQuestions(ActionEvent e){
		Stage stage=(Stage)questions.getScene().getWindow();
		stage.setScene(this.relatedQuestions);
	}
	public String getTitle(){return this.title.getText();}
	public void setRelatedQuestions(Scene questions){this.relatedQuestions=questions;}
	public void addItem(GridPane item){this.items.add(item);}
	public ArrayList<GridPane> getItems(){return this.items;}
	public void setTitle(String titolo){this.title.setText(titolo);}
}
