package iutdijon.projetrsabase.rsa;


/**
 * Description de la classe
 * @author Matthieu
 */
public class RabinMiller {

    
    //DEFI 16 - Méthode renvoyant si a est un témoin de Miller de n (preuve que n est composé)
    public static boolean temoin(NombreBinaire n, NombreBinaire a) throws Exception {
        boolean res = true;
        NombreBinaire un = n.soustraction(new NombreBinaire(1));
        NombreBinaire d= un;
        int s = 0;

        while(d.estPair()){
            d = d.quotient(new NombreBinaire(2));
            s++;
        }

        NombreBinaire x = a.puissanceModulo(d, n);

        if(x.estEgal(new NombreBinaire(1)) || x.estEgal(un)){
            res = false;
        }

        for(int i=0;i<s-1;i++){
            x = x.puissanceModulo(new NombreBinaire(2), n);

            if(x.estEgal(un)){
                res = false;
            }
        }

        return res;
    }
    
    //DEFI 19 - Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) throws Exception {

        boolean b = true;
        NombreBinaire n2 = new NombreBinaire(2);
        NombreBinaire nSus2 = n.soustraction(n2);

        for (int i=0;i<25;i++){

            NombreBinaire a = NombreBinaire.random(n2, nSus2);

            if(RabinMiller.temoin(n, a)){
                b = false;
                break;
            }
        }

        return b;
    }
    
    //DEFI 23 - Renvoie le plus petit nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) throws Exception {

        boolean isPrime = false;
        NombreBinaire un = new NombreBinaire(1);

        //tant que pas nombre premier
        while(!isPrime){
            //si est pair
            if(min.estPair()){
                min = min.addition(un);
            }else{
                //test si nombre premssss
                if(RabinMiller.testRabinMiller(min)){
                    isPrime = true;
                }else{
                    min = min.addition(un);
                }
            }
        }

        //retourne le plus petit nombre premier supérieur à min qui est passé en paramètre
        return min;
    }
}
