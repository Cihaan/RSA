package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;

import java.io.IOException;
import java.util.Random;

public class Defi6EstEgal extends Defi {

    private Network conn;

    /**
     * compare 2 nombre binaire
     * @return
     */
    @Override
    public void executer() throws Exception {

        Random random = new Random();
        boolean isOK = true;
        boolean isDone = false;

        //génération des nombres binaires aléatoires
        Integer randBin1 = (int) random.nextInt((555 - 0) + 1) + 0;
        Integer randBin2 = (int) random.nextInt((555 - 0) + 1) + 0;

        //debut du defi
        this.conn.sendMessage("-- Début du défi : Est Egal --");
        this.conn.sendMessage(String.valueOf(Integer.toBinaryString(randBin1)));
        this.conn.sendMessage(String.valueOf(Integer.toBinaryString(randBin2)));

        String nb1 = this.conn.receiveMessage();
        String nb2 = this.conn.receiveMessage();

        if (nb1.length() != nb2.length()) {
            this.conn.sendMessage("NOK");
            isDone = true;
        }
        else {
            for (int i = 0 ; i <= nb1.length() ; i++) {
                if (isDone == false) {
                    if (nb1.charAt(i) != nb2.charAt(i)) {
                        this.conn.sendMessage("NOK");
                        isDone = true;
                        isOK = false;
                    }
                }
            }

            if (isOK == true) {
                this.conn.sendMessage("OK");
            }
        }
    }
}
