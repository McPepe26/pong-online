/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Clases;

import Cliente.Interfaces.Comunicacion;
import Config.Interfaces.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ja-za
 */
public class ClienteUDP extends Thread implements Config {

    private DatagramSocket socket;
    private Comunicacion comunicacion;
    private InetAddress serverAddress;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private int jugadorNum;

    public ClienteUDP(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName(HOST);
            //socket.setSoTimeout(TIMEOUT);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mandar(String info) throws IOException {
        info += "["+jugadorNum+"]";
        byte[] bytesToSend = info.getBytes();
        sendPacket = new DatagramPacket(bytesToSend,
                bytesToSend.length, //Longitud del paquete 
                serverAddress, //Direccion del servidor
                PUERTO);  //Puerto del servidor
        socket.send(sendPacket);
        receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
    }

    public String recibir() throws IOException {
        socket.receive(receivePacket);
        if (!receivePacket.getAddress().equals(serverAddress)) {
            throw new IOException(
                    "Paquete recibido desde un destino desconocido!!!");
        }
        return new String(receivePacket.getData());
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

    public void cerrarConexion() {
        socket.close();
    }

    @Override
    public void run() {
        try {
            String jugador, parametros;
            mandar(NUEVOCLIENTE);
            jugador = recibir();
            System.out.println("Recibido rol");
            if (obtenerParametros(jugador)[0] == 1) {
                comunicacion.esJugador(true);
                jugadorNum = 1;
                System.out.println("Jugador 1");
            } else {
                comunicacion.esJugador(false);
                jugadorNum = 2;
                System.out.println("Jugador 2");
            }
            comunicacion.iniciarJuego();
            //socket.setSoTimeout(TIMEOUT);
            while (true) {
                try {
                    //mandamos los datos actuales
                    mandar(comunicacion.getParametros());
                    //Recibimos los datos del servidor
                    String recibido = recibir();
                    int datos[] = obtenerParametros(recibido);
                    //Los mandamos a la vista
                    comunicacion.setParametros(datos);
                } catch (IOException ex) {
                    
                }
            }
        } catch (IOException ex) {
            cerrarConexion();
            comunicacion.findelJuego();
            JOptionPane.showMessageDialog(null, "Ha terminado el juego");
        }
    }

}
