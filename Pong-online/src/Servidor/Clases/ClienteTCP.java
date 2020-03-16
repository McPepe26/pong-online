/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Clases;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author ja-za
 */
public class ClienteTCP extends Thread{
    private Socket socket;
    private DataOutputStream salida;    
}
