/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import Config.Interfaces.Config;
import Servidor.Interfaces.Comunicacion;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ja-za
 */
public class ServidorTCP extends Thread implements Config, Comunicacion {

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

    public void iniciarServidor() {
        try {
            System.out.println("Esperando conexiones...");
            do {
                clientes.add(new ClienteTCP(server.accept(), this));
            } while (clientes.size() < 2);
            System.out.println("Clientes registrados");
            String parametrosInicales = "[" + 385 + "][" + 235 + "]"
                    + "[" + 10 + "][" + 210 + "]"
                    + "[" + 775 + "][" + 210 + "]";
            clientes.get(0).mandar("J1");
            clientes.get(1).mandar("J2");
            System.out.println("Asignación de roles");
            clientes.get(0).mandar(parametrosInicales);
            clientes.get(1).mandar(parametrosInicales);
            System.out.println("Asignación de parametros iniciales");
            if(clientes.get(0).recibir().equals("ok") && clientes.get(1).recibir().equals("ok")){
                System.out.println("Incio del juego");
                clientes.get(0).start();
                clientes.get(1).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void difusion(String info) {
        for (ClienteTCP c : clientes) {
            c.setInfo(info);
        }
    }

}
