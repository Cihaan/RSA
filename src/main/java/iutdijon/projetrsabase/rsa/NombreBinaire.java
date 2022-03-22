package iutdijon.projetrsabase.rsa;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
    
    /**
     * Liste des bits du nombre binaire
     */
    private BitSet listeBits;
    
    /**
     * Taille forcée
     */
    private int tailleForcee = -1;
    
    // ------- METHODES DEJA IMPLEMENTEES --------------------------------------
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i,valeur);
    }
    
    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }
    
    
    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }
    
    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for(int i=0;i<nombre.listeBits.length();i++) {
            this.listeBits.set(i,nombre.listeBits.get(i));
        } 
    }
    
    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    
    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(s.length()-i-1) == '1') {
                this.listeBits.set(i,true);
            }
        }
    }
    
    //Renvoie la taille (en nb de bits)
    public int getTaille() {
        int valeur = 0;
        if(this.tailleForcee == -1) valeur =  this.listeBits.length();
        else valeur = this.tailleForcee;
        return valeur;
    }
    
    //Force la taille (en nb de bits)
    public void forcerTaille(int valeur) {
        this.tailleForcee = valeur;
    }
    
    
    //Affichage
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.getTaille();i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        if("".equals(res)) {
            res = "0";
        }
        return res;
    }
    
    
     //Scinde un nombreBinaire en nombreBinaire de taille donnée
     public ArrayList<NombreBinaire> scinder(int tailleMorceau) {
        ArrayList<NombreBinaire> res = new ArrayList<>();
        for(int i=0;i<this.getTaille();i=i+tailleMorceau) {
            NombreBinaire bitset = new NombreBinaire();
            for(int j=0;j<tailleMorceau;j++) {
                bitset.set(j,this.listeBits.get(i+j));
            }
            NombreBinaire nb = new NombreBinaire(bitset);
            nb.tailleForcee = tailleMorceau;
            res.add(nb);
        }
        return res;
    }
     
    //Concaténation de deux nombre binaires de taille : tailleMorceau
     public NombreBinaire concatenation(NombreBinaire mot) {
         NombreBinaire sortie = new NombreBinaire(this);
         int taille = this.getTaille()+mot.getTaille();
         for(int i=0;i<taille;i++) {
             if(i<this.getTaille()) sortie.listeBits.set(i,this.listeBits.get(i));
             else sortie.listeBits.set(i,mot.listeBits.get(i-this.getTaille()));
         }
         sortie.forcerTaille(taille);
         return sortie;
     }
     
     //-------------------------------------------------------------------------

    //DEFI 2 - Renvoie le résultat de l'addition de this avec mot2
    public NombreBinaire addition(NombreBinaire mot2) {
        //initialisation variables
        int r = 0;
        int taille = 0;

        boolean b1 = false;
        boolean b2 = false;

        //série de if
        if(mot2.getTaille() < this.getTaille()){
            taille = this.getTaille();
        }
        else{
            taille = mot2.getTaille();
        }

        NombreBinaire nb = new NombreBinaire(mot2);

        //beurk
        for(int i = 0; i <= taille; i++){
            b1 = this.get(i);
            b2 = mot2.get(i);

            if(r == 1){
                r = 0;
                if(b1 == true){
                    if(b2 == true){
                        r = 1;
                        nb.set(i, true);
                    }
                    else{
                        r = 1;
                        nb.set(i, false);
                    }
                }
                else{
                    if(b2 == true){
                        r = 1;
                        nb.set(i, false);
                    }
                    else{
                        nb.set(i, true);
                    }
                }
            }
            else{
                if(b1 == true){
                    if(b2 == true){
                        r = 1;
                        nb.set(i, false);
                    }
                    else{
                        nb.set(i, true);
                    }
                }
                else{
                    if(b2 == true){
                        nb.set(i, true);
                    }
                    else{
                        nb.set(i, false);
                    }
                }
            }
        }

        return nb;
    }
     
     //DEFI 3 - Caclule le décalage de n bits (multiplie par 2^n)
     public NombreBinaire decalage(int n) {
        //initialisation variables
        String decal = "";
        for (int i = 0; i<n; i++){
            decal += "0";
        }
        return new NombreBinaire(this.toString() + decal);
     }
     
     //DEFI 4 - renvoie le resultat de l'addition de this avec mot3
     public NombreBinaire soustraction(NombreBinaire mot2) {
        //iniitalisation variables
        int r = 0;
        NombreBinaire new_n = new NombreBinaire();

        //pour tout les chiffres de this
        for(int i = 0 ; i < getTaille(); i++){
            int b1 = this.get(i) == false ? 0 : 1;
            int b2 = mot2.get(i) == false ? 0 : 1;

            int res = b1 - b2 - r;

            if(res == 1){
                new_n.set(i,true);
                r = 0;
            }
            else if(res == 0){
                new_n.set(i,false);
                r = 0;
            }
            else if(res == -1){
                r = 1;
                new_n.set(i,true);
            }
            else if(res == -2){
                r = 1;
                new_n.set(i,false);
            }

        }
        return new NombreBinaire(new_n);
     }
     
     //DEFI 5 - Renvoie si this est plus petit ou égal à mot2
     public boolean estInferieurA(NombreBinaire mot2) {

        // si taille de a différent de taille b return true
        if(this.getTaille() != mot2.getTaille()) return getTaille() <= mot2.getTaille();

        int taille = this.getTaille();

        //sinon vérifier pour tous les chiffres de this
        for(int i = 0; i < taille; i++){
            int rI = taille - i-1;

            int b1 = this.get(rI) == false ? 0 : 1;
            int b2 = mot2.get(rI) == false ? 0 : 1;

            if(b1 != b2){
                return b1 <= b2;
            }
        }

        return false;
     }
     
     //DEFI 6 - Renvoie si this est égal à mot2 ou non
     public boolean estEgal(NombreBinaire mot2)
     {
         //si taille supérieur retourner false directement
         if (this.getTaille() != mot2.getTaille()) {
             return false;
         }
         else {
             //sinon vérifier chaque chiffres
             for (int i = 0 ; i <= this.getTaille() ; i++) {
                 if (this.get(i) != mot2.get(i)) {
                     return false;
                 }
             }

             return true;
         }
     }
     
     //DEFI 7 - Renvoie si un nombre est pair
     public boolean estPair() {
        //si fini par 0 retouner true
        if(this.toString().endsWith("0")){
            return true;
        }
        else{
            //sinon retourner false
            return false;
        }
     }
     
     //DEFI 8 - Calcul la multiplication de this avec mot2
     public NombreBinaire multiplication(NombreBinaire mot2) {
        //initialisation variables
        NombreBinaire nb = new NombreBinaire();

        //multiplication
        for(int i = 0; i < mot2.getTaille(); i++){
            if(mot2.get(i)){
                nb = nb.addition(this.decalage(i));
            }
        }

        return nb;
     }

     //DEFI 9 - Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire mot2)
     {
         //initialisation variables
         NombreBinaire a = this;
         NombreBinaire b = mot2;
         NombreBinaire r = a;
         NombreBinaire q = new NombreBinaire(0);
         NombreBinaire un = new NombreBinaire(1);

         //si reste pas inférieur a b
         while(!r.estInferieurA(b)) {
             //effectuer division euclidienne
             int n = r.getTaille() - b.getTaille();
             NombreBinaire bPrime = b.decalage(n);

             if( !bPrime.estInferieurA(r) && !bPrime.estEgal(r) ) {
                 bPrime = b.decalage(n-1);
                 n -= 1;
             }
             r = r.soustraction(bPrime);
             q = q.addition(un.decalage(n));

         }
         //sinon retourner quotient
         return  q;
     }
     
     //DEFI 10 - Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire mot2) {

         //initialisation variables
         NombreBinaire a = this;
         NombreBinaire b = mot2;
         NombreBinaire r = a;
         NombreBinaire q = new NombreBinaire(0);
         NombreBinaire un = new NombreBinaire(1);


         // division euclidienne
         while(!r.estInferieurA(b)) {
             int n = r.getTaille() - b.getTaille();
             NombreBinaire bPrime = b.decalage(n);

             if( !bPrime.estInferieurA(r) && !bPrime.estEgal(r) ) {
                 bPrime = b.decalage(n-1);
                 n -= 1;
             }
             r = r.soustraction(bPrime);
             q = q.addition(un.decalage(n));

         }
         //retourner quotient au lieu de reste
         return  r;
     }  
     
    //DEFI 11 - Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
        Random rand = new Random();

        // Taille du nombre binaire
        int n =  (int)(Math.random() * taille);
        // Récupère 0 ou 1
        int m = 0;
        String s= "";

        for(int i = 0; i<n; i++){
            m = (int)Math.random();
            s += "" + m;
        }
        NombreBinaire nb = new NombreBinaire(s);
        return nb;
    }
    
     //DEFI 12 - Calcul de this^exposant modulo m par exponentiation modulaire rapide
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
         return null;
     }
     
     //DEFI 13 - Calcul le PGCD de this et mot2
     public NombreBinaire PGCD(NombreBinaire mot2) {
        NombreBinaire a = new NombreBinaire(this);
        NombreBinaire temp = new NombreBinaire(a);

        if(a.estInferieurA(mot2)){
            a = mot2;
            mot2 = temp;
        }
         NombreBinaire zero = new NombreBinaire(0);
        while(!mot2.estEgal(zero)){
            temp = mot2;
            mot2 = a.modulo(mot2);
            a = temp;
        }
        return a;
     }
     
    //DEFI 14 - renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {
        NombreBinaire new_n = new NombreBinaire();
        max = max.soustraction(new NombreBinaire("1"));

        boolean max_dif = false;
        boolean min_dif = false;

        for(int i = max.getTaille()-1; i >= 0; i--){
            Boolean maxI = max.get(i);
            Boolean minI = min.get(i);

            if(max_dif) maxI = true;
            if(min_dif) minI = false;

            boolean val;

            if(minI == true && maxI == true){
                val = true;
            }
            else if(maxI == false && minI == false){
                val = false;
            }
            else{
                val = Long.valueOf(Math.round(Math.random())).intValue() == 1 ? true : false;
            }
            new_n.set(i, val);

            if(!max_dif && val == false && max.get(i) == true) max_dif = true;
            if(!min_dif && val == true && min.get(i) == false) min_dif = true;

        }
        return new_n;
    }
    
     //DEFI 15 - Calcul de l'inverse modulo nombre
     //Basé sur l'algo d'euclide étendu (adapté).
     public NombreBinaire inverseModulaire(NombreBinaire nombre) {

        //deja implémenté

         NombreBinaire ZERO = new NombreBinaire(0);
            
         NombreBinaire n0 = new NombreBinaire(nombre);
         NombreBinaire b0 = new NombreBinaire(this);
         NombreBinaire t0 = new NombreBinaire(0);
         NombreBinaire t = new NombreBinaire(1);
         
         NombreBinaire q = n0.quotient(b0);
         NombreBinaire r = n0.modulo(b0);
         while(!r.estEgal(ZERO)) {
             NombreBinaire produit = q.multiplication(t);
             NombreBinaire memoire;
             //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
             if(t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
             }
             else {
                memoire = t0.soustraction(produit).modulo(nombre);  
             }
             
             t0 = t;
             t = memoire;
             n0 = b0;
             b0 = r;
             q = n0.quotient(b0);
             r = n0.modulo(b0);
         }
         return t;
     }
     
}
