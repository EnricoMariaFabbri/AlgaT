package sample;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
public class Controller{
private
    Stage stage;
	Scene startPage;
	ArrayList<Scene> lessons;
	ArrayList<Scene> questions;
public 
	Controller(Stage s){
		this.stage=s;
		this.startPage=new Scene(new PaginaIniziale(this),2000,1000);
		this.questions=new ArrayList<Scene>();
		this.lessons=new ArrayList<Scene>();
		this.load(this.findLessonsPath());
		Collections.reverse(this.lessons);
		Collections.reverse(this.questions);
	}
	
	void goStartPage(){this.stage.setScene(this.startPage);}
	void changeScene(Scene scene){this.stage.setScene(scene);}
	
	String findLessonsPath(){
		try{
			URI uri=ClassLoader.getSystemClassLoader().getResource("AlgaT/Lezioni").toURI();
			if(uri.getScheme().equals("file")){//se sono sull'Ide
				return uri.getRawPath();
			}else{//se lavoro dal jar
				String path= uri.toString().split("\\:")[2];
				String finalPath=path.split("/AlgaT.jar!")[0];
				this.extractLessons(finalPath);
				return finalPath+"/AlgaT/Lezioni";
			}
		}catch(Exception e){e.printStackTrace();}
		return "CIAO";
	}
	
	void extractLessons(String directory){
		try{
			ArrayList<String> cmd=new ArrayList<String>();
			cmd.add("jar");
			cmd.add("xvf");
			cmd.add("AlgaT.jar");
			cmd.add("AlgaT/Lezioni/");
			ProcessBuilder pr=new ProcessBuilder(cmd);
			pr.directory(new File(directory));
			pr.start();
	}catch(Exception e){e.printStackTrace();}
	}
	
	void load(String pathName){
	try{
	File path=new File(pathName);
	System.out.println(path.getPath());
	File[] lessons=path.listFiles();//estrapolo tutte le cartelle lezione
	for(File lesson:lessons){		//per ogni cartella lezione
		File slidesPath=new File(path+"/"+lesson.getName()+"/Slides");//creo il path delle slide della lezione
		File questionsPath=new File(path+"/"+lesson.getName()+"/Domande");//creo il path delle domande della lezione
		Lezione lezione=new Lezione(this,lesson.getName());//creo l'oggetto lezione
		this.loadSlides(slidesPath, lezione);
		this.loadQuestions(questionsPath,lezione);
		this.lessons.add(new Scene(lezione,2000,1000));
		}
	}catch(Exception e){e.printStackTrace();}
	}
	
	void loadSlides(File path,Lezione lezione){
		try{
		File[] slides=path.listFiles();//estrapolo le slides
		for(File slide:slides){//per ogni slides
			String slideName=slide.getName().split("\\.")[0];//estrapolo solo il nome della classe senza estensione
			String classPackage="AlgaT.Lezioni."+lezione.getTitle()+".Slides."+slideName;//creo il nome del package
			GridPane slaiz=(GridPane)Class.forName(classPackage).newInstance();// creo la slide della lezione
			lezione.addItem(slaiz);	//aggiungo la slide alla lista delle slide della lezione
			}
		//Collections.reverse(lezione.items);
		}catch(Exception e){e.printStackTrace();}
	}
	
	void loadQuestions(File path,Lezione lezione){
		try{
			Domande domande=new Domande(this,"Domande della "+lezione.getTitle());
			File[] questions=path.listFiles();//estrapolo le domande
			for(File question:questions){//per ogni domanda
				String questionName=question.getName().split("\\.")[0];//estrapolo solo il nome della classe senza estensione
				String classPackage="AlgaT.Lezioni."+lezione.getTitle()+".Domande."+questionName;//creo il nome del package
				GridPane domanda=(GridPane)Class.forName(classPackage).newInstance();// creo la domanda
				domande.addItem(domanda);	//aggiungo la domanda alla lista delle domande della lezione
				}
			Collections.reverse(domande.items);
			this.questions.add(new Scene(domande,2000,1000));
			}catch(Exception e){e.printStackTrace();}
	}
}
