/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Interfaces;

/**
 *
 * @author ja-za
 */
public interface Comunicacion {
    void iniciarJuego();
    void esJugador(boolean esJugador1);
    void setParametros(int params[]);
    String getParametros();
}
