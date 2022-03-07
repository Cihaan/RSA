/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client de connexion TCP
 * @author Matthieu
 */
public class Network {
    private Socket socket; 
    private PrintWriter pw;
    private BufferedReader bufr;
    private boolean con = false;
    private int PORT = 1234;
    private String IP = "127.0.0.1";
    
    /**
     * Constructeur
     * @throws IOException 
     */
    public Network() throws IOException {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        this.con = false;
    }

    /**
     * Création de la socket
     * @throws IOException
     */
    private void creationSocket() throws IOException {
        this.socket = new Socket(this.IP ,this.PORT);
    }

    public boolean isConnected() {
        return (this.con);
    }

    /**
     * créer la gestion de flux
     * @throws IOException
     */
    public void creationFlux() throws IOException {
        //Création du gestionnaire de flux entrant
        InputStreamReader iSReader = new InputStreamReader(this.socket.getInputStream());
        this.bufr = new BufferedReader(iSReader);
        //Création du gestionnaire de flux sortant
        this.pw = new PrintWriter(this.socket.getOutputStream(),true);
    }

    /**
     * créer la connexion avec le serveur
     * @throws IOException
     */
    public void createCon() throws IOException {
        this.creationSocket();
        this.creationFlux();
        this.con=true;
=======
        socket = new Socket("127.0.0.1",1234);
        pw = new PrintWriter(socket.getOutputStream(), true);
        bufr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
>>>>>>> Stashed changes
=======
        socket = new Socket("127.0.0.1",1234);
        pw = new PrintWriter(socket.getOutputStream(), true);
        bufr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
>>>>>>> Stashed changes
    }
    
    /**
     * Envoi d'un message
     */
    public void sendMessage(String message) {
        System.out.println(">> "+message);
        this.pw.println(message);
    }
        
    /**
     * Réception d'un message
     */
    public String receiveMessage() throws IOException {
        String message = this.bufr.readLine();
        System.out.println("<< "+message);
        return message;
    }
    
    /**
     * Fin de la connexion
     */
    public void end() throws IOException {
        socket.close();
    }
}
