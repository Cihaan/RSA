package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;

public class Defi6EstEgal extends Defi {

    @Override
    public void executer() throws Exception {
        Network conn = new Network();
        conn.receiveMessage();

        String msg = conn.receiveMessage();

        NombreBinaire nb1;
        NombreBinaire nb2;

        while (!msg.startsWith("Defi valide")) {
            nb1 = new NombreBinaire(msg);
            nb2 = new NombreBinaire(conn.receiveMessage());

            Boolean resultat = nb1.estEgal(nb2);

            conn.sendMessage(resultat.toString());

            if (conn.receiveMessage().startsWith("NOK")) {
                throw new Exception("VALEUR INVALIDE");
            }

            msg = conn.receiveMessage();
        }

        conn.receiveMessage();
        conn.end();

    }
}
