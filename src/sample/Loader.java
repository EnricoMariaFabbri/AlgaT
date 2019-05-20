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

	public ArrayList<String> getFileList(String path){
		ArrayList<String> files = new ArrayList<String>();
		try{
			//zona da controllare poichè nel jar non va ma in teoria dovrebbe andare
			URL url=getClass().getResource(path);
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
			String lessonsPath = "Lezioni";
			for (String lesson : this.getFileList(lessonsPath)) {
				String currentLessonPath = lessonsPath + "/" + lesson;
				String slidesPath = currentLessonPath + "/Slides";
				String questionsPath = currentLessonPath + "/Domande";
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
				this.loadQuestions(questionsPath, lesson, questionController);
				c.addLesson(new Scene(lezione,2000,1000));
			}
		}catch(Exception e){e.printStackTrace();}
		}

		public void loadSlides(String slidesPath,Lezione lezione){
			for(String slide:this.getFileList(slidesPath)){
				String slideToLoad=slidesPath+"/"+slide;
				try{
				GridPane slaiz=FXMLLoader.load(getClass().getResource(slideToLoad));
				lezione.addItem(slaiz);    //aggiungo la slide alla lista delle slide della lezione
				}catch(Exception e){e.printStackTrace();}
			}
		}

		public void loadQuestions(String questionsPath,String numLezione,Domande domande){
			for(String domanda:this.getFileList(questionsPath)){
				String questionToLoad=questionsPath+"/"+domanda;
				try {
					GridPane questione=FXMLLoader.load(getClass().getResource(questionToLoad));
					domande.addItem(questione);
				}catch(Exception e){e.printStackTrace();}
			}
		}
}
