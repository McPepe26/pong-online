/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author narut
 */
public class Raqueta {
    private int x;
    private int y;
    private final int ANCHO = 10, ALTO = 80;
    protected int cont = 0,ran = 0;
    public Raqueta(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Rectangle getRaqueta() {
        return new Rectangle(x, y, ANCHO, ALTO);
    }
    public void moverR1(Rectangle limites) {
        if (EventoTeclado.w && y > limites.getMinY()) {
            y--;
        }
        if (EventoTeclado.s && y < limites.getMaxY()-ALTO) {
            y++;
        }
    }

    public void moverR2(Rectangle limites) {
        if (EventoTeclado.up && y > limites.getMinY()) {
            y--;
        }
        if (EventoTeclado.down && y < limites.getMaxY()-ALTO) {
            y++;
        }
    }
    
    public void setXY(int[] pos){
        x = pos[0];
        y = pos[1];
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
