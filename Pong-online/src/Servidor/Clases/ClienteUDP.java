/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import Config.Interfaces.Config;
import Servidor.Interfaces.Comunicacion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ja-za
 */
public class ClienteUDP extends Thread implements Config{

    private DatagramSocket socket;
    private DatagramPacket packet;
    private String info;
    private Comunicacion comunicacion;

    public ClienteUDP(DatagramSocket socket, Comunicacion comunicacion) throws IOException {
        this.socket = socket;
        this.comunicacion = comunicacion;
        info = "";
    }

    public void mandar(String msg) throws IOException {
        byte[] bytesToSend = msg.getBytes();
        packet.setData(bytesToSend);
        socket.send(packet);
    }

    public String recibir() throws IOException {
        socket.receive(packet);
        return new String(packet.getData());
    }

    @Override
    public void run() {
        while (true) {
            try {
                int[] datosPos = new int[6];
                packet = new DatagramPacket(new byte[225], 225);
                String datos = recibir();
                for (int i = 0; datos.length() > 0; i++) {
                    int fin = datos.indexOf("]");
                    datosPos[i] = Integer.parseInt(datos.substring(1, fin));
                    datos = datos.substring(fin + 1);
                }
                comunicacion.difusion("[" + datosPos[0] + "][" + datosPos[1] + "]"
                                    + "[" + datosPos[2] + "][" + datosPos[3] + "]"
                                    + "[" + datosPos[4] + "][" + datosPos[5] + "]", this);

                mandar(info);
            } catch (IOException ex) {
                System.out.println("Conexion perdida");
                break;
            }
        }
        cerrarConexion();
    }
    
    public void cerrarConexion(){
        socket.close();
        comunicacion.cerrarConexiones(this);
    }
    
    public boolean estaConectado(){
        return !socket.isClosed();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
