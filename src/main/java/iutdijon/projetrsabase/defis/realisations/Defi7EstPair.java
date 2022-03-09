package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;

import java.io.IOException;

/**
 * 7ème défi : Est pair
 * @author Guillaume
 */
public class Defi7EstPair extends Defi {
    @Override
    public void executer() throws Exception {
        Network network = new Network();
        network.receiveMessage();
        String message = network.receiveMessage();
        while(!message.startsWith("Defi valide")){
            NombreBinaire nb = new NombreBinaire(message);
            if(nb.estPair()){
                network.sendMessage("true");
            }
            else{
                network.sendMessage("false");
            }

            if(network.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

            message = network.receiveMessage();
        }

        network.receiveMessage();
        network.end();
    }
}
