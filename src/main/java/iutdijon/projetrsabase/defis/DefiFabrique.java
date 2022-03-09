package iutdijon.projetrsabase.defis;

import iutdijon.projetrsabase.defis.realisations.*;

/**
 * Fabrique des défis
 * @author Matthieu
 */
public class DefiFabrique {
    
    /**
     * Crée le défi
     * @param numeroDuDefis numéro du défi à créer
     * @return le défi
     */
    public static Defi creer(int numeroDuDefis) {
        Defi defi = null;
        
        switch(numeroDuDefis) {
            case 1 : defi = new Defi1ConnexionAuServeur(); break;
            case 3 : defi = new Defi3Decalage(); break;
            case 4 : defi = new Defi4Soustraction();break;
            case 5 : defi = new Defi5EstInferieur(); break;
            default : throw new UnsupportedOperationException("Défis non implémenté !");
        }
        
        return defi;
    }
    
}
