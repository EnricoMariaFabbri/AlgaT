package sample;

import java.util.Arrays;

public class Domanda {
    private
        String domanda;
        String[] opzioni;
        String risposta;
        String spiegazione;
        public
    Domanda(String riga1,String riga2,String riga3){
            this.domanda=riga1;
            this.opzioni=riga2.split("\\;");
            this.risposta=riga3.split("\\;")[0];
            this.spiegazione=riga3.split("\\;")[1];
        }
        String getDomanda(){return this.domanda;}
        String[] getOpzioni(){return this.opzioni;}
        String getRisposta(){return this.risposta;}
        String getSpiegazione(){return this.spiegazione;}
        boolean isStructuredWell(){//la risposta è presente nelle opzioni?
            if(!Arrays.asList(this.opzioni).contains(this.risposta)){
                return false;
            }
            return true;
        }

}
