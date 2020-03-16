/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.Interfaces;

import Servidor.Clases.ClienteUDP;

/**
 *
 * @author ja-za
 */
public interface Comunicacion {
    void difusion(String info, ClienteUDP cliente);
    void cerrarConexiones(ClienteUDP cliente);
}
