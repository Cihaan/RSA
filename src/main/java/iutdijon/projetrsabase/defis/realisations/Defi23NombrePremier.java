/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import iutdijon.projetrsabase.rsa.RabinMiller;
import java.io.IOException;

/**
 *
 * @author travail
 */
public class Defi23NombrePremier extends Defi{

    @Override
    public void executer() throws Exception {
        Network conn = new Network();
        String messageM = conn.receiveMessage();

        NombreBinaire n;
        while(!messageM.startsWith("Defi valide"))
        {
            n = new NombreBinaire(conn.receiveMessage());

            NombreBinaire resultat = RabinMiller.nombrePremier(n);

            conn.sendMessage(resultat.toString());

            if(conn.receiveMessage() == "NOK"){
                System.out.println("erreur nok");
            };

            // Vérifie si c'est juste
            messageM = conn.receiveMessage();
        }
        conn.end();
    }

}