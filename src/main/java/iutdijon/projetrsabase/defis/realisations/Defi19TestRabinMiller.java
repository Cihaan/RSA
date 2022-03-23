package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import iutdijon.projetrsabase.rsa.RabinMiller;

public class Defi19TestRabinMiller extends Defi {
    @Override
    public void executer() throws Exception {
        Network conn = new Network();
        conn.receiveMessage();

        String msg = conn.receiveMessage();

        NombreBinaire n;

        while (!msg.startsWith("Defi valide")) {
            NombreBinaire naaaaa = new NombreBinaire(msg);

            n = new NombreBinaire(naaaaa);

            Boolean resultat = RabinMiller.testRabinMiller(n);

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
