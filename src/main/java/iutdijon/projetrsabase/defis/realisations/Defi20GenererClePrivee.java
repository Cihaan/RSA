package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.GenerateurDeClesRSA;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi20GenererClePrivee extends Defi {
    @Override
    public void executer() throws Exception {
        Network network = new Network();
        network.receiveMessage();

        String messageP = network.receiveMessage();


        while(!messageP.startsWith("Defi valide")){

            String messageQ = network.receiveMessage();

            String messageE = network.receiveMessage();

            NombreBinaire P = new NombreBinaire(messageP);
            NombreBinaire Q = new NombreBinaire(messageQ);
            NombreBinaire e = new NombreBinaire(messageE);

            GenerateurDeClesRSA cle = new GenerateurDeClesRSA();



            NombreBinaire nb = cle.genererClePrive(P, Q, e);

            nb.forcerTaille(nb.getTaille());


            network.sendMessage(nb.toString());

            if(network.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

            messageP = network.receiveMessage();
        }

        network.receiveMessage();
        network.end();
    }
}
