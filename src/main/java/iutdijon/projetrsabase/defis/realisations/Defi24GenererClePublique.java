package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.GenerateurDeClesRSA;

public class Defi24GenererClePublique extends Defi {
    @Override
    public void executer() throws Exception {
        Network network = new Network();
        String message = network.receiveMessage();

        while(!message.startsWith("Defi valide")){
            GenerateurDeClesRSA.genererClePublique();

            network.sendMessage(GenerateurDeClesRSA.getP().toString());
            network.sendMessage(GenerateurDeClesRSA.getQ().toString());
            network.sendMessage(GenerateurDeClesRSA.getN().toString());
            network.sendMessage(GenerateurDeClesRSA.getPhi().toString());
            network.sendMessage(GenerateurDeClesRSA.getE().toString());

            if(network.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

        }

        network.end();
    }
}
