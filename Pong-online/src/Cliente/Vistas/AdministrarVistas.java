/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import Cliente.Clases.ClienteTCP;
import Cliente.Interfaces.Comunicacion;
import Cliente.Interfaces.ManejarEventos;

/**
 *
 * @author ja-za
 */
public class AdministrarVistas implements ManejarEventos, Comunicacion{

    private ClienteTCP cliente;

    public AdministrarVistas() {
        cliente = new ClienteTCP(this);
    }
    
    
    @Override
    public void iniciarCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniciarJuego() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void esJugador(boolean esJugador1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParametros(int[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getParametros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
