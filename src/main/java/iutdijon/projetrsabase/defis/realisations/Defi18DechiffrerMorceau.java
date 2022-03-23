package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.AlgorithmeRSA;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi18DechiffrerMorceau extends Defi {
    @Override
    public void executer() throws Exception {
        Network net = new Network();
        net.receiveMessage();

        String message = net.receiveMessage();


        NombreBinaire morceau;
        NombreBinaire N;
        NombreBinaire d;
        while(!message.startsWith("Defi valide")){
            morceau = new NombreBinaire(message);
            N = new NombreBinaire(net.receiveMessage());
            d = new NombreBinaire(net.receiveMessage());

            NombreBinaire retval = AlgorithmeRSA.dechiffrerMorceau(morceau, N, d);
            net.sendMessage(retval.toString());

            if(net.receiveMessage().startsWith("NOK")){
                throw new Exception("VALEUR INVALIDE");
            }

            message = net.receiveMessage();
        }

        net.receiveMessage();
        net.end();

    }
}
