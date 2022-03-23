package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import iutdijon.projetrsabase.rsa.RabinMiller;

public class Defi16RabinMiller extends Defi {
    @Override
    public void executer() throws Exception {
        Network conn = new Network();
        conn.receiveMessage();

        String msg = conn.receiveMessage();

        NombreBinaire n;
        NombreBinaire a;

        while (!msg.startsWith("Defi valide")) {
            n = new NombreBinaire(msg);
            a = new NombreBinaire(conn.receiveMessage());

            Boolean resultat = RabinMiller.temoin(n, a);

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