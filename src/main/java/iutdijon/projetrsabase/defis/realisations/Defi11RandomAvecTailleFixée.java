package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;

import java.io.IOException;

/**
 * 7ème défi : Est pair
 * @author Guillaume
 */
public class Defi11RandomAvecTailleFixée extends Defi{
    @Override
    public void executer() throws Exception {
        Network network = new Network();
        network.receiveMessage();
        String message = network.receiveMessage();
        int taille = 0;

        while(!message.startsWith("Defi valide")){
            taille = Integer.valueOf(message);

            NombreBinaire nb = NombreBinaire.randomAvecTailleMax(taille);

            network.sendMessage(String.valueOf(nb));

            if(network.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

            message = network.receiveMessage();
        }

        network.receiveMessage();
        network.end();
    }
}
