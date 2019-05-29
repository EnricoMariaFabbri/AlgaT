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

	public ArrayList<String> getContent(String path){
		ArrayList<String> files = new ArrayList<String>();
		try{
			//zona da controllare poichè nel jar non va ma in teoria dovrebbe andare
			InputStream stream =Loader.class.getResourceAsStream(path);
			//fine zona controllo
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
				String currentLessonPath = "Lezioni/" + lesson;
				String slidesPath = currentLessonPath + "/Slides";
				String questionsPath = currentLessonPath + "/domande.txt";
				FXMLLoader lessonLoader = new FXMLLoader(getClass().getResource("Lezione.fxml"));
				GridPane lezione = lessonLoader.load();
				Lezione lessonController = lessonLoader.getController();
				lessonController.setTitle(lesson);
				FXMLLoader questionLoader = new FXMLLoader(getClass().getResource("Domande.fxml"));
				GridPane domande = questionLoader.load();
				Domande questionController = questionLoader.getController();
				questionController.setTitle(lesson);
				lessonController.setRelatedQuestions(new Scene(domande, 2000, 1000));
				this.loadSlides(slidesPath, lessonController);
				this.loadQuestions(questionsPath,questionController);
				c.addLesson(new Scene(lezione,2000,1000));
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
			int numDomande=fileDomande.size()/3;
			int i=0;
			for(int j=0;j<numDomande;j++){
			    String rigaDomanda=fileDomande.get(i);
			    String rigaOpzioni=fileDomande.get(i+1);
			    String rigaRisposta=fileDomande.get(i+2);
			    domande.addItem(new Domanda(rigaDomanda,rigaOpzioni,rigaRisposta));
			    i=i+3;
            }
			domande.populatePane(domande.getItems().get(0));//imposto come finesta iniziale la prima domanda
		}
}
