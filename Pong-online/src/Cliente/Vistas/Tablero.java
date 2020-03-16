/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import Cliente.Interfaces.ManejarEventos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author narut
 */
public class Tablero extends JFrame {

    private final int ancho = 800, alto = 500;
    private Juego game;
    private Movimiento move;

    public Tablero(ManejarEventos manejador) {
        setTitle("ExamenPong");
        setSize(ancho, alto);
        setLocationRelativeTo(null); //ubicando la ventana en el centro de la pantalla
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                JOptionPane.showMessageDialog(null, "Ha terminado el juego");
                manejador.finJuego();
            }
        });
        game = new Juego(manejador);
        add(game);
        addKeyListener(new EventoTeclado());
        move = new Movimiento(this);
    }
    
    public void setParametros(int[] datos) {
        game.setParametros(datos);
    }
    
    public int[] getParametros(){
        return game.getParametros();
    }
    
    public void iniciar(){
        move.start();
        setVisible(true);
    }
    
    public void detener(){
        move.interrupt();
    }
    
    public void setJugador(boolean esJugador1){
        game.setEsJugador1(esJugador1);
    }
}
