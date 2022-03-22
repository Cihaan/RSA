package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi14RandomAvecBorne extends Defi {

    @Override
    public void executer() throws Exception {
        Network network = new Network();
        network.receiveMessage();
        String message = network.receiveMessage();
        int taille = 0;

        while(!message.startsWith("Defi valide")){
            NombreBinaire min = new NombreBinaire(message);

            message = network.receiveMessage();
            NombreBinaire max = new NombreBinaire(message);

            NombreBinaire nb = NombreBinaire.random(min,max);

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
