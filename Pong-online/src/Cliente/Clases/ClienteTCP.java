/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Clases;

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
public class ClienteTCP extends Thread implements Config{
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public ClienteTCP() {
        try {
            socket = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mandar(String info) throws IOException{
        salida.writeUTF(info);
    }
    
    public String recibir() throws IOException{
        return entrada.readUTF();
    }
    
}
