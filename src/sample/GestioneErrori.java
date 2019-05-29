package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Optional;

public class GestioneErrori {
    public static void showAlert(String messaggio){
        Alert dialogoAllerta = new Alert(Alert.AlertType.WARNING, messaggio);
        dialogoAllerta.showAndWait();
    }

    public static boolean checkLesson(String stringa){
        try{
            Integer lesson = Integer.parseInt(stringa);
            if(lesson>=Navigator.getLessonsSize() || lesson<0){
                showAlert("NON ESISTE LA LEZIONE");
                return false;
            }
        }catch(Exception e){showAlert("NON È UN NUMERO");return false;}
    return true;
    }
}
