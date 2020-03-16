/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import Cliente.Clases.ClienteTCP;
import Cliente.Interfaces.Comunicacion;
import Cliente.Interfaces.ManejarEventos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ja-za
 */
public class AdministrarVistas implements ManejarEventos, Comunicacion{

    private ClienteTCP cliente;
    private Tablero tab;
    private ClientePrincipal clientePrincipal;

    public AdministrarVistas() {
        tab = new Tablero(this);
        clientePrincipal = new ClientePrincipal(this);
    }
    
    public void iniciar(){
        clientePrincipal.setVisible(true);
    }
    
    @Override
    public void iniciarCliente() {
        //Iniciamos el hilo para comunicarse al servidor
        cliente = new ClienteTCP(this);
        cliente.start();
    }

    @Override
    public void iniciarJuego() {
        //Iniciamos la vista del juego
        tab.iniciar();
        clientePrincipal.setVisible(false);
    }

    @Override
    public void esJugador(boolean esJugador1) {
        tab.setJugador(esJugador1);
    }

    @Override
    public void setParametros(int[] params) {
        tab.setParametros(params);
    }

    @Override
    public String getParametros() {
        int parametros[] = tab.getParametros();
        
        return "[" + parametros[0] + "][" + parametros[1] + "]" + 
               "[" + parametros[2] + "][" + parametros[3] + "]" + 
               "[" + parametros[4] + "][" + parametros[5] + "]";
    }

    @Override
    public void finJuego() {
        cliente.interrupt();
        cliente.cerrarConexion();
        tab.detener();
        findelJuego();
    }

    @Override
    public void findelJuego() {
        tab.setVisible(false);
        tab = new Tablero(this);
        clientePrincipal.activar();
    }

}
