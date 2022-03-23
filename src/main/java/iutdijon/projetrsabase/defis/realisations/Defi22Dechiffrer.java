package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.AlgorithmeRSA;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi22Dechiffrer extends Defi {
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
            NombreBinaire d = new NombreBinaire(messageE);

            NombreBinaire nb = AlgorithmeRSA.dechiffrer(morceau, N, d);

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
