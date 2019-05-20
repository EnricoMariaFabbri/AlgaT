package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class Navigator extends FXMLLoader {
private
	static ArrayList<Scene> lessons;
public 
	Navigator(){
	this.lessons=new ArrayList<Scene>();
	new Loader().load(this);
	//Collections.reverse(this.lessons);
	}

	static Scene getLesson(Integer index){ return lessons.get(index); }
	void addLesson(Scene lesson){this.lessons.add(lesson);}
	static int getLessonsSize(){return lessons.size();}
}
