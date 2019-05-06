package AlgaT.Lezioni.Lezione1.Slides;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Slide0 extends GridPane {
private
	Text testo;
public
	Slide0(){
		this.testo=new Text();
		this.testo.setText("SONO AL SLIDE 0 DELLA LEZIONE 1");
		this.add(testo,0,1);
	}
public static void main(String[] args){
	Slide0 sl=new Slide0();
}
}