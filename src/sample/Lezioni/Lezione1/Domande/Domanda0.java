package AlgaT.Lezioni.Lezione1.Domande;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Domanda0 extends GridPane{
private
	Text testo;
public
	Domanda0(){
		this.testo=new Text();
		this.testo.setText("SONO LA DOMANDA 0 DELLA LEZIONE 1");
		this.add(this.testo,0,1);
	}
} 
