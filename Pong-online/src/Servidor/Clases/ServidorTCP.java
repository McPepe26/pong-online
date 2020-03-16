/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import Config.Interfaces.Config;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ja-za
 */
public class ServidorTCP extends Thread implements Config{
    private ServerSocket server;
    private LinkedList<ClienteTCP> clientes;

    public ServidorTCP() {
        try {
            server = new ServerSocket(PUERTO);
            clientes = new LinkedList<>();
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
