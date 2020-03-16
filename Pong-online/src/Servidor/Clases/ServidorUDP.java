/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import Config.Interfaces.Config;
import Servidor.Interfaces.Comunicacion;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ja-za
 */
public class ServidorUDP extends Thread implements Config, Comunicacion {

    private DatagramSocket server;
    private LinkedList<ClienteUDP> clientes;
    private boolean hayJuego;

    public ServidorUDP() {
        try {
            server = new DatagramSocket(PUERTO);
            clientes = new LinkedList<>();
            hayJuego = false;
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarServidor() {
        try {
            System.out.println("Esperando conexiones...");
            do {
                
                clientes.add(new ClienteUDP(server, this));
                System.out.println("Cliente "+clientes.size()+" registrado");
            } while (clientes.size() < 2);
            System.out.println("Clientes registrados");
            String parametrosInicales = "[" + 385 + "][" + 235 + "]"
                                      + "[" + 10 + "][" + 210 + "]"
                                      + "[" + 0 + "][" + 0 + "]";
            
            if(clientes.get(0).recibir().equals("ok") && clientes.get(1).recibir().equals("ok")){
                clientes.get(0).mandar("J1");
                clientes.get(1).mandar("J2");
                System.out.println("Asignación de roles");
                clientes.get(0).mandar(parametrosInicales);
                clientes.get(1).mandar(parametrosInicales);
                System.out.println("Asignación de parametros iniciales");
                System.out.println("Incio del juego");
                clientes.get(0).start();
                clientes.get(1).start();
                hayJuego = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void difusion(String info, ClienteUDP cliente) {
        for (ClienteUDP c : clientes) {
            if(cliente != c)
                c.setInfo(info);
        }
    }

    @Override
    public void cerrarConexiones(ClienteUDP cliente) {
        clientes.remove(cliente);
        for (ClienteUDP c : clientes) {
            c.cerrarConexion();
        }
        hayJuego = false;
    }

    @Override
    public void run() {
        while(true){
            try {
                if(!hayJuego){
                    iniciarServidor();
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

}
