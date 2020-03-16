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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ja-za
 */
public class ClienteTCP extends Thread implements Config {

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Comunicacion comunicacion;

    public ClienteTCP(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
        try {
            socket = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mandar(String info) throws IOException {
        salida.writeUTF(info);
    }

    public String recibir() throws IOException {
        return entrada.readUTF();
    }

    public int[] obtenerParametros(String datos) {
        int[] datosPos = new int[6];
        for (int i = 0; datos.length() > 0; i++) {
            int fin = datos.indexOf("]");
            datosPos[i] = Integer.parseInt(datos.substring(1, fin));
            datos = datos.substring(fin + 1);
        }
        return datosPos;
    }
    
    @Override
    public void run() {
        try {
            String jugador, parametros;
            mandar("ok");
            jugador = recibir();
            System.out.println("Recibido rol");
            parametros = recibir();
            System.out.println("Recibidos los parametros iniciales");
            if (jugador.equals("J1")) 
                //Asignar a la vista el jugador 1
                comunicacion.esJugador(true);
            else 
                //Asignar a la vista el jugador 2
                comunicacion.esJugador(false);
            comunicacion.setParametros(obtenerParametros(parametros));
            comunicacion.iniciarJuego();
            while (true) {
                //mandamos los datos actuales
                mandar(comunicacion.getParametros());
                //Recibimos los datos del servidor
                int datos[] = obtenerParametros(recibir());
                //Los mandamos a la vista
                comunicacion.setParametros(datos);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
