package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Domande{
	private
	@FXML
	GridPane currentQuestion;
	@FXML
	Label title;
	@FXML
	Button startPage;
	@FXML
	Button rispondi;
	ToggleGroup radioGroup;
	ArrayList<Domanda> questions;
	int i;//semplice contatore

	public Domande(){
		this.questions=new ArrayList<Domanda>();
		this.radioGroup=new ToggleGroup();//serve per gestire i vari radioButton
		this.i=0;
	}

	public void goToStartPage(ActionEvent e){
		try {
			Pane paginaIniziale = FXMLLoader.load(getClass().getResource("PaginaIniziale.fxml"));
			Stage stage=(Stage)startPage.getScene().getWindow();
			stage.setScene(new Scene(paginaIniziale,2000,1000));
		}catch(Exception exc){exc.printStackTrace();}
	}

	public void checkRisposta(ActionEvent e){
		Domanda domandaCorrente=this.questions.get(this.i);
		if(GestioneErrori.isSelectedRisp(this.radioGroup)){
			RadioButton rd = (RadioButton) this.radioGroup.getSelectedToggle();
			String rispostaUtente = rd.getText();
			if (!rispostaUtente.equals(domandaCorrente.getRisposta())) {
				GestioneErrori.showAlert("risposta ERRATA,la risposta è:\n" + domandaCorrente.getRisposta()+","+domandaCorrente.getSpiegazione());
			} else {
				GestioneErrori.showAlert("risposta ESATTA");
			}
			if(!GestioneErrori.isLimitReached(this.i,this.questions.size())) {
				this.i++;
				this.populatePane(this.questions.get(this.i));
			}
		}
	}

	public void populatePane(Domanda dom){
		this.radioGroup=new ToggleGroup();//pulisco il gruppo di radioButton
		this.currentQuestion.getChildren().removeAll(this.currentQuestion.getChildren());//PULISCO IL CURRENT ITEM,POICHÈ LE DOMANDE NON SONO DEI GRIDPANE
		Label domanda=new Label(dom.getDomanda());
		this.currentQuestion.add(domanda,0,1);
		int j=1;//la linea su cui inserire la radioButton
		for(String opt:dom.getOpzioni()){//per ogni domanda,gli associo un radioButton
			RadioButton rd=new RadioButton(opt);
			rd.setToggleGroup(this.radioGroup);
			this.currentQuestion.add(rd,1,j);
			j++;
		}
	}

	public void addItem(Domanda item){this.questions.add(item);}
	public ArrayList<Domanda> getItems(){return this.questions;}
	public void setTitle(String titolo){this.title.setText("Domande della "+titolo);}
}
