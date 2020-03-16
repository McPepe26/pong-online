/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import Servidor.Interfaces.Comunicacion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ja-za
 */
public class ClienteTCP extends Thread{
    private Socket socket;
    private String info;
    private DataOutputStream salida;    
    private DataInputStream entrada;
    private Comunicacion comunicacion;

    public ClienteTCP(Socket socket, Comunicacion comunicacion) throws IOException {
        this.socket = socket;
        this.comunicacion = comunicacion;
        
        salida =  new DataOutputStream(socket.getOutputStream());
        entrada = new DataInputStream(socket.getInputStream());
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
