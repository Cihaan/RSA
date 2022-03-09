package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi3Decalage extends Defi {
    @Override
    public void executer() throws Exception {
        Network net = new Network();
        net.receiveMessage();

        String message = net.receiveMessage();

        NombreBinaire nb;
        while(!message.startsWith("Defi valide")){
            nb = new NombreBinaire(message);
            int nDecal = Integer.valueOf(net.receiveMessage());

            net.sendMessage(nb.decalage(nDecal).toString());

            if(net.receiveMessage().startsWith("NOK")){
                throw new Exception("VALEUR INVALIDE");
            }

            message = net.receiveMessage();
        }

        net.receiveMessage();
        net.end();
    }
}
