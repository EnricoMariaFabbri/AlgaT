package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Loader {

	public ArrayList<String> getContent(String path){//ottengo tutto il contenuto del file path
		ArrayList<String> files = new ArrayList<String>();
		try{
			InputStream stream =Loader.class.getResourceAsStream(path);
			InputStreamReader isr =new InputStreamReader(stream);
			BufferedReader buf=new BufferedReader(isr);
			String line = buf.readLine();
				while (line != null){
					files.add(line);
					line=buf.readLine();
				}
				buf.close();
		}catch(Exception e){e.printStackTrace();}
		return files;
		}

		public  void load(Navigator c){
		try {
			String lessonsPath = "Lezioni/index.txt";
			for (String lesson : this.getContent(lessonsPath)) {
				String currentLessonPath = "Lezioni/" + lesson;//imposto il path della lezione da caricare
				String slidesPath = currentLessonPath + "/Slides";//imposto il path delle slide della lezione da caricare
				String questionsPath = currentLessonPath + "/domande.txt";//imposto il path delle domande della lezione da caricare
				FXMLLoader lessonLoader = new FXMLLoader(getClass().getResource("Lezione.fxml"));//punto la grafica della lezione
				GridPane lezione = lessonLoader.load();//carico la grafica della lezione
				c.addLesson(new Scene(lezione,2000,1000));//la aggiungo
				Lezione lessonController = lessonLoader.getController();//ottengo il controller della grafica della lezione
				lessonController.setTitle(lesson);
				FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("Domande.fxml"));//punto la grafica delle domande
				GridPane domande = questionLoader.load();//carico la grafica della domanda
				Domande questionController = questionLoader.getController();//ottengo il controller della grafica della domanda
				questionController.setTitle(lesson);
				lessonController.setRelatedQuestions(new Scene(domande, 2000, 1000));//associo alla lezione la relativa sezione domande
				this.loadSlides(slidesPath, lessonController);//carico le slide sul controller della lezione
				this.loadQuestions(questionsPath,questionController);//carico le domande sul controller delle domande associate alla lezione
			}
		}catch(Exception e){e.printStackTrace();}
		}

		public void loadSlides(String slidesPath,Lezione lezione){
			for(String slide:this.getContent(slidesPath+"/index.txt")){
				String slideToLoad=slidesPath+"/"+slide;
				try{
					GridPane slaiz=FXMLLoader.load(getClass().getResource(slideToLoad));
					lezione.addItem(slaiz);    //aggiungo la slide alla lista delle slide della lezione
				}catch(Exception e){e.printStackTrace();}
			}
			lezione.setCurrentItem(0);//import subito la prima slide
		}

		public void loadQuestions(String questionsPath,Domande domande){
	        ArrayList<String> fileDomande=this.getContent(questionsPath);
			int numDomande=fileDomande.size()/3;//una domanda è composta da 3 righe
			int i=0;
			for(int j=0;j<numDomande;j++){
			    String rigaDomanda=fileDomande.get(i);
			    String rigaOpzioni=fileDomande.get(i+1);
			    String rigaRisposta=fileDomande.get(i+2);
			    domande.addItem(new Domanda(rigaDomanda,rigaOpzioni,rigaRisposta));//aggiungo la domanda al controller della sezione domande
			    i=i+3;
            }
			domande.populatePane(domande.getItems().get(0));//imposto come finesta iniziale la prima domanda
		}
}
