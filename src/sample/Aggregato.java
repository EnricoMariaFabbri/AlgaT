package sample;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Aggregato extends GridPane{
private 
	GridPane currentItem;
	ArrayList<GridPane> items;
	Controller c;
	int i=0;//semplice contatore
	Button nextItem;
	Button prevItem;
	Button goToStartPage;
	Label title;
public
	Aggregato(Controller controller,String titolo){
		this.c=controller;
		this.currentItem=new GridPane();
		this.add(currentItem,0,1);
		this.items=new ArrayList<GridPane>();
		this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(10);
		this.setVgap(10);
		this.title=new Label(titolo);
		this.title.setFont(new Font(20));
		this.add(title,0,0);
		this.nextItem=new Button("NEXT");
		this.add(nextItem,0,3);
		this.prevItem=new Button("PREV");
		this.add(prevItem,0,4);
		this.goToStartPage=new Button("TORNA AL MENU PRINCIPALE");
		this.add(goToStartPage,0,5);

		nextItem.setOnAction(new EventHandler<ActionEvent>() {		
			public void handle(ActionEvent e) {
				getChildren().remove(currentItem);
				currentItem=items.get(i);
				add(currentItem,0,1);
				i++;
			}
		});
		prevItem.setOnAction(new EventHandler<ActionEvent>() {		
			public void handle(ActionEvent e) {
				i--;
				getChildren().remove(currentItem);
				currentItem=items.get(i);
				add(currentItem,0,1);
			}
		});
		goToStartPage.setOnAction(new EventHandler<ActionEvent>() {		
			public void handle(ActionEvent e) {
				c.changeScene(c.startPage);
			}
		});
	}
	String getTitle(){return this.title.getText();}
	void addItem(GridPane item){this.items.add(item);}
	ArrayList<GridPane> getItems(){return this.items;}
}
