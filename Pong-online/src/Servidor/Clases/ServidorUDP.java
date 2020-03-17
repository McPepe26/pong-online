/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import Config.Interfaces.Config;
import Servidor.Interfaces.Comunicacion;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ja-za
 */
public class ServidorUDP extends Thread implements Config {

    private DatagramSocket server;
    private LinkedList<int[]> datos;
    private LinkedList<DatagramPacket> clientes;

    public ServidorUDP() {
        try {
            server = new DatagramSocket(PUERTO);
            clientes = new LinkedList<>();
            datos = new LinkedList<>();
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarServidor() {
        try {
            boolean asignados = false;
            String nomCliente;
            System.out.println("Servidor iniciado");
            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
                server.receive(packet);
                String comando = new String(packet.getData());
                int data[] = obtenerParametros(comando);
                if (data[0] == 100000) {
                    datos.add(new int[7]);
                    clientes.add(packet);
                } else if (clientes.size() == 2) {
                    datos.set(data[6]-1, data);
                    packet = clientes.get(data[6]-1);
                    if (data[6] == 1)
                        packet.setData(convertirParametros(datos.get(1)).getBytes());
                    else if (data[6] == 2)
                        packet.setData(convertirParametros(datos.get(0)).getBytes());
                    server.send(packet);
                }
                if(clientes.size() == 2 && !asignados){
                    nomCliente = "["+1+"]";
                    packet = clientes.get(0);
                    packet.setData(nomCliente.getBytes());
                    server.send(packet);
                    System.out.println("Cliente " + 1 + " agregado");
                    
                    nomCliente = "["+2+"]";
                    packet = clientes.get(1);
                    packet.setData(nomCliente.getBytes());
                    server.send(packet);
                    System.out.println("Cliente " + 2 + " agregado");
                    asignados = true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int[] obtenerParametros(String datos) {
        int[] datosPos = new int[7];
        try {
            for (int i = 0; datos.length() > 0; i++) {
                int fin = datos.indexOf("]");
                datosPos[i] = Integer.parseInt(datos.substring(1, fin));
                datos = datos.substring(fin + 1);
            }
        }catch(StringIndexOutOfBoundsException ex){
            
        }
        return datosPos;
    }

    public String convertirParametros(int[] datos) {
        String params = "";
        for (int i = 0; i < datos.length; i++) {
            params += "[" + datos[i] + "]";
        }
        return params;
    }

}
