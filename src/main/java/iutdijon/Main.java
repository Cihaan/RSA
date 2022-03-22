package iutdijon;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.defis.DefiFabrique;
import iutdijon.projetrsabase.rsa.NombreBinaire;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main
 * @author Matthieu
 */
public class Main {

    /**
     * Lancement du client
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Defi defi = DefiFabrique.creer(10);
        try {
            defi.executer();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*NombreBinaire nb1 = new NombreBinaire("11101100");
        NombreBinaire nb2 = new NombreBinaire("100");
        System.out.println(nb1.quotient(nb2));*/
    }
    
}
