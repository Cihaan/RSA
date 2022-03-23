package iutdijon.projetrsabase.rsa;

import java.util.Random;

/**
 * Générateur de clé RSA
 * @author Matthieu
 */
public class GenerateurDeClesRSA {

    private static NombreBinaire P;
    private static NombreBinaire Q;
    private static NombreBinaire N;
    private static NombreBinaire phi;
    private static NombreBinaire e;

    public static NombreBinaire getP() {
        return P;
    }

    public static NombreBinaire getQ() {
        return Q;
    }

    public static NombreBinaire getN() {
        return N;
    }

    public static NombreBinaire getPhi() {
        return phi;
    }

    public static NombreBinaire getE() {
        return e;
    }
    
    
    //Défi 24 - Génère la clé publique (P,Q,N,phi et e)    
    public static void genererClePublique() throws Exception {
        NombreBinaire P = new NombreBinaire();
        P = NombreBinaire.random(new NombreBinaire(100), new NombreBinaire(200));
        P = RabinMiller.nombrePremier(P);

        NombreBinaire Q = new NombreBinaire();
        Q = NombreBinaire.random(new NombreBinaire(50), new NombreBinaire(99));
        Q = RabinMiller.nombrePremier(Q);

        NombreBinaire N = P.multiplication(Q);

        NombreBinaire pSoustrait = P.soustraction(new NombreBinaire(1));
        NombreBinaire qSoustrait = Q.soustraction(new NombreBinaire(1));
        NombreBinaire Phi = pSoustrait.multiplication(qSoustrait);

        NombreBinaire E = RabinMiller.nombrePremier(Phi);

        setP(P);
        setQ(Q);
        setN(N);
        setPhi(Phi);
        setE(E);
    }
    
    //Défi 20 - Renvoie la clé privée d
    public static NombreBinaire genererClePrive(NombreBinaire P,NombreBinaire Q,NombreBinaire e) {
        setP(P);
        setQ(Q);
        setE(e);

        NombreBinaire temp = new NombreBinaire(1);

        NombreBinaire phi = P.soustraction(temp).multiplication(Q.soustraction(temp));
        setPhi(phi);

        temp = e.inverseModulaire(phi);

        temp.forcerTaille(ParametresRSA.getTailleCle());


        return temp;
    }


    public static void setP(NombreBinaire p) {
        P = p;
    }

    public static void setQ(NombreBinaire q) {
        Q = q;
    }

    public static void setN(NombreBinaire n) {
        N = n;
    }

    public static void setPhi(NombreBinaire phi) {
        GenerateurDeClesRSA.phi = phi;
    }

    public static void setE(NombreBinaire e) {
        GenerateurDeClesRSA.e = e;
    }
}
