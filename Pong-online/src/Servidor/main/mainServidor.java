/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.main;

import Servidor.Clases.ServidorUDP;

/**
 *
 * @author ja-za
 */
public class mainServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServidorUDP servidor = new ServidorUDP();
        servidor.iniciarServidor();
    }
    
}
