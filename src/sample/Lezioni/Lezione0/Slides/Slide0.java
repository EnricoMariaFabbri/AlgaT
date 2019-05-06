package AlgaT.Lezioni.Lezione0.Slides;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Slide0 extends GridPane{
	private
		Text testo;
	public
		Slide0(){
			this.testo=new Text();
			this.testo.setText("SONO LA SLIDE 0 DELLA LEZIONE 0");
			this.add(this.testo,0,1);
		}
}
