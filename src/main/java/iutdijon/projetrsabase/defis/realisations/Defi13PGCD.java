package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;

import java.io.IOException;

/**
 * 7ème défi : Est pair
 * @author Guillaume
 */
public class Defi13PGCD extends Defi {
    @Override
    public void executer() throws Exception {
        Network net = new Network();
        net.receiveMessage();

        String message = net.receiveMessage();


        NombreBinaire nb1;
        NombreBinaire nb2;
        while(!message.startsWith("Defi valide")){
            nb1 = new NombreBinaire(message);
            nb2 = new NombreBinaire(net.receiveMessage());

            NombreBinaire nbF = nb1.PGCD(nb2);

            net.sendMessage(nbF.toString());

            if(net.receiveMessage().startsWith("NOK")){
                throw new Exception("VALEUR INVALIDE");
            }

            message = net.receiveMessage();
        }

        net.receiveMessage();
        net.end();
    }
}
