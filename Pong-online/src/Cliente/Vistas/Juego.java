/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import Cliente.Interfaces.ManejarEventos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author narut
 */
public class Juego extends JPanel {

    private Pelota pelo = new Pelota(385, 235);
    private Raqueta raq = new Raqueta(10, 210);
    private Raqueta raq2 = new Raqueta(775, 210);
    private javax.swing.JLabel Jugador1;
    private ManejarEventos manejador;
    private boolean esJugador1;

    public Juego(ManejarEventos manejador) {
        setBackground(Color.black);
        this.manejador = manejador;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        Actualizar();
        dibujar(g2);
    }

    public void dibujar(Graphics2D g) {
        g.fill(pelo.getPelota());
        g.fill(raq.getRaqueta());
        g.fill(raq2.getRaqueta());
        g.drawString(pelo.getPuntuacion1() + " - " + pelo.getPuntuacion2(), 380, 20);
    }

    public void Actualizar() {
        if(esJugador1){
            raq.moverR2(getBounds());
            pelo.mover(getBounds(), Choque(raq.getRaqueta()), Choque2(raq2.getRaqueta()));
            if(pelo.getPuntuacion1() == 5 || pelo.getPuntuacion2() == 5){
                manejador.finJuego();
                JOptionPane.showMessageDialog(null, "Ha terminado el juego");
            }
        }
        else
            raq2.moverR2(getBounds());
    }

    public boolean Choque(Rectangle raque) {
        return pelo.getPelota().intersects(raque);
    }

    public boolean Choque2(Rectangle raque) {
        return pelo.getPelota().intersects(raque);
    }

    public void setParametros(int[] datos) {
        if(esJugador1){
            raq2.setX(datos[2]);
            raq2.setY(datos[3]);
        }else{
            pelo.setX(datos[0]);
            pelo.setY(datos[1]);
            raq.setX(datos[2]);
            raq.setY(datos[3]);
            pelo.setPuntuacion1Cliente(datos[4]);
            pelo.setPuntuacion2Cliente(datos[5]);
        }
    }
    
    public int[] getParametros(){
        int raqX, raqY;
        if(esJugador1){
            raqX = raq.getX();
            raqY = raq.getY();
        }else{
            raqX = raq2.getX();
            raqY = raq2.getY();
        }
        
        return new int[]{pelo.getX(), pelo.getY(), raqX, raqY, pelo.getPuntuacion1(), pelo.getPuntuacion2()};
    }

    public void setEsJugador1(boolean esJugador1) {
        this.esJugador1 = esJugador1;
    }
}
