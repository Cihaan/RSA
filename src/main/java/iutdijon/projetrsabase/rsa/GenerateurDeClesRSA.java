package iutdijon.projetrsabase.rsa;

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
    public static void genererClePublique() {
        
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
