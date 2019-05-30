package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Lezione {
private
	@FXML
	GridPane currentSlide;
	@FXML
	Label title;
	@FXML
	Button nextSlide;
	@FXML
	Button prevSlide;
	@FXML
	Button startPage;
	@FXML
	Button questions;
	ArrayList<GridPane> slides;
	int i;//semplice contatore
	Scene relatedQuestions;

	public Lezione(){
		this.slides=new ArrayList<GridPane>();
		this.i=0;
	}

	public void prev(ActionEvent e){
		if(!GestioneErrori.isLimitReached(this.i)){//se non sono gia alla prima slide
			this.i--;//carico l'indice precedente
		}
		this.setCurrentItem(this.i);//carico la slide precedente
	}

	public void next(ActionEvent e){
		if(!GestioneErrori.isLimitReached(this.i,this.slides.size())){//se non sto superando il numero di slide
			this.i++;// carico l'indice successivo
		}
		this.setCurrentItem(this.i);//carico la slide successiva
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

	public void setCurrentItem(int index){
		GridPane root=(GridPane)nextSlide.getParent();//ottengo il gridpane root della scena
		root.getChildren().remove(currentSlide);//ELIMINO IL CURRENT ITEM PERCHÈ LE SLIDE SONO GIÀ DEI GRIDPANE,QUINDI NON HA SENSO PULIRE E REINSERIRE OGNI ELEMENTO
		this.currentSlide=this.slides.get(index);//imposto come slide corrente,la slide all'indice index
		root.add(this.currentSlide,0,1);//aggiungo la slide alla "scena"
	}

	public void setRelatedQuestions(Scene questions){this.relatedQuestions=questions;}
	public void addItem(GridPane item){this.slides.add(item);}
	public void setTitle(String titolo){this.title.setText(titolo);}
}
