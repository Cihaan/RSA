package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;

import java.io.IOException;

/**
 * 1er défi : Connexion au serveur
 * @author Matthieu
 */
public class Defi1ConnexionAuServeur extends Defi {

    @Override
    public void executer() throws IOException {
        Network network = new Network();
        network.receiveMessage();
        String message = network.receiveMessage();
        while(!message.startsWith("Defi valide")){
            network.sendMessage(String.valueOf(Integer.valueOf(message)+1));
            if(network.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

            message = network.receiveMessage();
        }

        network.receiveMessage();
        network.end();
    }
    
}
