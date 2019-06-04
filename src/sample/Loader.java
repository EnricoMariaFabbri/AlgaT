package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Loader {

	public ArrayList<String> getContent(String path){//ottengo tutto il contenuto del file path
		ArrayList<String> files = new ArrayList<String>();
		try{
			FileReader reader= new FileReader(path);
			BufferedReader buf=new BufferedReader(reader);
			String line = buf.readLine();
				while (line != null){
					files.add(line);
					line=buf.readLine();
				}
				buf.close();
		}catch(Exception e){e.printStackTrace();}
		return files;
		}

		public  void load(Navigator c){//funzione di caricamento principale(root)
		try {
			String lessonsPath = System.getProperty("user.dir")+"/Lezioni/index.txt";
			for (String lesson : this.getContent(lessonsPath)) {
				String currentLessonPath =System.getProperty("user.dir")+"/Lezioni/" + lesson;//imposto il path della lezione da caricare
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

		public void loadSlides(String slidesPath,Lezione lezione) {//funzione di caricamento delle slide della lezione
			try {//catturo l'errore se non è presente index.txt nella cartella slides
				for (String slide : this.getContent(slidesPath + "/index.txt")) {
					String slideToLoad = slidesPath + "/" + slide;
						URL slideUrl = new File(slideToLoad).toURI().toURL();
						try {//catturo l'errore se non esiste la slide
							GridPane slaiz = FXMLLoader.load(slideUrl);
							lezione.addItem(slaiz);    //aggiungo la slide alla lista delle slide della lezione
						}catch(Exception e){e.printStackTrace();continue;}
				}
				lezione.setCurrentItem(0);//import subito la prima slide
			}catch(Exception e){e.printStackTrace();}
		}

		public void loadQuestions(String questionsPath,Domande domande) {//funzione di caricamento delle domande della lezione
			try {//catturo l'errore se non esiste il file domande.txt
				ArrayList<String> fileDomande = this.getContent(questionsPath);
					int numDomande = fileDomande.size() / 3;//una domanda è composta da 3 righe
					int i = 0;
					for (int j = 0; j < numDomande; j++) {//
						String rigaDomanda = fileDomande.get(i);
						String rigaOpzioni = fileDomande.get(i + 1);
						String rigaRisposta = fileDomande.get(i + 2);
						Domanda dom=new Domanda(rigaDomanda, rigaOpzioni, rigaRisposta);
						if(dom.isStructuredWell()) {//se la domanda è strutturata bene
							domande.addItem(dom);//aggiungo la domanda al controller della sezione domande
						}
						i = i + 3;
					}
					domande.populatePane(domande.getItems().get(0));//imposto come finesta iniziale la prima domanda
			}catch(Exception e){e.printStackTrace();}
		}
}
