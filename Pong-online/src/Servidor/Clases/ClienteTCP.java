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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void mandar(String msg) throws IOException{
        salida.writeUTF(msg);
    }
    
    public String recibir() throws IOException{
        return entrada.readUTF();
    }

    @Override
    public void run() {
        while(true){
            try {
                int[] datosPos = new int[6];
                String datos = recibir();
                for (int i = 0; datos.length() > 0; i++) {
                    int fin = datos.indexOf("]");
                    datosPos[i] = Integer.parseInt(datos.substring(1, fin));
                    datos = datos.substring(fin + 1);
                }
                comunicacion.difusion("[" + datosPos[0] + "][" + datosPos[1] + "]" + 
                                      "[" + datosPos[2] + "][" + datosPos[3] + "]" + 
                                      "[" + datosPos[4] + "][" + datosPos[5] + "]");
                if (info.equals(""))
                    info = "[" + datosPos[0] + "][" + datosPos[1] + "]" + 
                           "[" + datosPos[2] + "][" + datosPos[3] + "]" + 
                           "[" + datosPos[4] + "][" + datosPos[5] + "]";
                mandar(info);
            } catch (IOException ex) {
                Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
