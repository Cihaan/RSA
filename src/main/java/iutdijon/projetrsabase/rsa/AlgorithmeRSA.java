package iutdijon.projetrsabase.rsa;

import java.util.ArrayList;

/**
 * Description de la classe
 * @author Matthieu
 */
public class AlgorithmeRSA {

    
    //DEFI 17 - Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public static NombreBinaire chiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire e){
        NombreBinaire res = morceau.puissanceModulo(e, N);

        res.forcerTaille(ParametresRSA.getTailleCle());
        return res;
    }
   
    //DEFI 18 - Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public static NombreBinaire dechiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire d){
       return null;
    }

    //DEFI 21 - Chiffre le message avec les clés données
    public static NombreBinaire chiffrer(NombreBinaire messageAChiffrer, NombreBinaire N, NombreBinaire e) {
        ArrayList<NombreBinaire> listNb = messageAChiffrer.scinder(ParametresRSA.getTailleMorceau());

        NombreBinaire nb1 = listNb.get(0);
        NombreBinaire nb2 = listNb.get(1);

        NombreBinaire res = AlgorithmeRSA.chiffrerMorceau(nb1, N, e).concatenation(AlgorithmeRSA.chiffrerMorceau(nb2, N, e));

        return res;
    }

    //DEFI 22 - Déchiffre le message avec les clés données
    public static NombreBinaire dechiffrer(NombreBinaire messageADechiffrer, NombreBinaire N, NombreBinaire d) {
        return null;
    }
}
