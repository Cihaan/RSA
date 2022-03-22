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
            case 2 : defi = new Defi2Addition(); break;
            case 3 : defi = new Defi3Decalage(); break;
            case 4 : defi = new Defi4Soustraction();break;
            case 5 : defi = new Defi5EstInferieur(); break;
            case 6 : defi = new Defi6EstEgal(); break;
            case 7 : defi = new Defi7EstPair(); break;
            case 8 : defi = new Defi8Multiplication(); break;
            case 9 : defi = new Defi9Quotient(); break;
            case 10 : defi = new Defi10Modulo(); break;
            case 11 : defi = new Defi11RandomAvecTailleFixée(); break;
            case 15 : defi = new Defi15InversementModulaire(); break;
            default : throw new UnsupportedOperationException("Défis non implémenté !");
        }
        
        return defi;
    }
    
}
