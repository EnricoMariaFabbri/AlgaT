package sample;

import javafx.scene.control.*;
import sample.Navigator;

import java.util.ArrayList;
import java.util.Optional;

public class GestioneErrori {
    public static void showAlert(String messaggio){
        Alert dialogoAllerta = new Alert(Alert.AlertType.WARNING, messaggio);
        dialogoAllerta.showAndWait();
    }

    public static boolean controlLessonIndex(String stringa,int maxsize){//controlle se è un numero e se rispetta l'intervallo
        try{
            int i= Integer.parseInt(stringa);
            if(i<0 || i>maxsize-1){
                showAlert("NON è COMPRESO NELL'INTERVALLO ATTESO");
                return false;
            }
        }catch(Exception e){showAlert("NON È UN NUMERO");return false;}
        return true;
    }

    public static boolean isSelectedRisp(ToggleGroup t){//controllo se ho selezionato una risposta
        if(t.getSelectedToggle()==null){
            showAlert("DEVI SELEZIONARE UN'OPZIONE");
            return false;
        }
        return true;
    }

    public static boolean isLimitReached(int index,int sup){//controllo se sto superando il limite superiore
        if (index== sup-1) {
            GestioneErrori.showAlert("SEI GIÀ ARRIVATO IN FONDO");
            return true;
        }
        return false;
    }

    public static boolean isLimitReached(int index){//controllo se sto superando il limite inferiore
        if (index== 0) {
            GestioneErrori.showAlert("SEI GIÀ ALL'INIZIO");
            return true;
        }
        return false;
    }
}
