package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.AlgorithmeRSA;
import iutdijon.projetrsabase.rsa.GenerateurDeClesRSA;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi17ChiffrerMorceau extends Defi {
    @Override
    public void executer() throws Exception {
        Network network = new Network();
        network.receiveMessage();

        String messageM = network.receiveMessage();


        while(!messageM.startsWith("Defi valide")){

            String messageN = network.receiveMessage();
            String messageE = network.receiveMessage();

            NombreBinaire morceau = new NombreBinaire(messageM);
            NombreBinaire N = new NombreBinaire(messageN);
            NombreBinaire e = new NombreBinaire(messageE);

            NombreBinaire nb = AlgorithmeRSA.chiffrerMorceau(morceau, N, e);

            network.sendMessage(nb.toString());

            if(network.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

            messageM = network.receiveMessage();
        }

        network.receiveMessage();
        network.end();
    }
}
