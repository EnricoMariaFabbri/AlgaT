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
	GridPane currentItem;
	@FXML
	Label title;
	@FXML
	Button startPage;
	@FXML
	Button rispondi;
	ToggleGroup radioGroup;
	ArrayList<Domanda> items;
	int i;//semplice contatore

	public Domande(){
		this.items=new ArrayList<Domanda>();
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
		Domanda domandaCorrente=this.items.get(this.i);
		RadioButton rd=new RadioButton();
		if(this.radioGroup.getSelectedToggle()==null){
			Alert dialogoAllerta = new Alert(Alert.AlertType.WARNING,"Devi selezionare un'opzione");
			dialogoAllerta.showAndWait();
		}else {
			rd = (RadioButton) this.radioGroup.getSelectedToggle();
			String rispostaUtente = rd.getText();
			if (!rispostaUtente.equals(domandaCorrente.getRisposta())) {
				Alert dialogoAllerta = new Alert(Alert.AlertType.WARNING, "risposta errata,la risposta è:\n" + domandaCorrente.getRisposta());
				dialogoAllerta.showAndWait();
			} else {
				Alert dialogoAllerta = new Alert(Alert.AlertType.WARNING, "risposta esatta");
				dialogoAllerta.showAndWait();
			}
			if (this.i == this.items.size() - 1) {
				Alert dialogoAllerta = new Alert(Alert.AlertType.WARNING, "Hai risposto a tutte le domande");
				dialogoAllerta.showAndWait();
			} else {
				this.i++;
				this.populatePane(this.items.get(this.i));
			}
		}
	}

	public void populatePane(Domanda dom){
		this.currentItem.getChildren().removeAll(this.currentItem.getChildren());
		Label domanda=new Label(dom.getDomanda());
		this.currentItem.add(domanda,0,1);
		int j=1;//la linea su cui inserire la radioButton
		for(String opt:dom.getOpzioni()){
			RadioButton rd=new RadioButton(opt);
			rd.setToggleGroup(this.radioGroup);
			this.currentItem.add(rd,1,j);
			j++;
		}
	}

	public String getTitle(){return this.title.getText();}
	public void addItem(Domanda item){this.items.add(item);}
	public ArrayList<Domanda> getItems(){return this.items;}
	public void setTitle(String titolo){this.title.setText("Domande della "+titolo);}
}
