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
    private boolean esServidor;
    private ManejarEventos manejador;

    public Juego(boolean esServidor, ManejarEventos manejador) {
        setBackground(Color.black);
        this.esServidor = esServidor;
        this.manejador = manejador;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);

        if (esServidor) {
            Actualizar();
        }else{
            raq2.moverR2(getBounds());
        }
        dibujar(g2);
    }

    public void dibujar(Graphics2D g) {
        g.fill(pelo.getPelota());
        g.fill(raq.getRaqueta());
        g.fill(raq2.getRaqueta());
        g.drawString(pelo.getPuntuacion1() + " - " + pelo.getPuntuacion2(), 380, 20);
    }

    public void Actualizar() {
        pelo.mover(getBounds(), Choque(raq.getRaqueta()), Choque2(raq2.getRaqueta()));
        raq.moverR1(getBounds());
    }

    public boolean Choque(Rectangle raque) {
        return pelo.getPelota().intersects(raque);
    }

    public boolean Choque2(Rectangle raque) {
        return pelo.getPelota().intersects(raque);
    }

    public void setDatos(int[] datos) {
        pelo.setX(datos[0]);
        pelo.setY(datos[1]);
        raq.setX(datos[2]);
        raq.setY(datos[3]);
        pelo.setPuntuacion1Cliente(datos[4]);
        pelo.setPuntuacion2Cliente(datos[5]);
    }
    
    public int[] getPosRaqueta(){
        return new int[]{raq2.getX(), raq2.getY()};
    }
    
    public int[] getDatosPos(int xR, int yR){
        raq2.setX(xR);
        raq2.setY(yR);
        return new int[]{pelo.getX(), pelo.getY(), raq.getX(), raq.getY(), pelo.getPuntuacion1(), pelo.getPuntuacion2()};
    }

}
