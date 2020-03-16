/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author narut
 */
public class Pelota {

    private int x;
    private int y;
    private final int ANCHO = 30, ALTO = 30;
    private int dx = 1, dy = 1;
    
    protected int cont = 0, ran = 0;
    private int puntuacion1;
    private int puntuacion2;

    public int getPuntuacion1() {
        return puntuacion1;
    }

    public void setPuntuacion1(int puntuacion1) {
        this.puntuacion1 =this.puntuacion1  + puntuacion1;
    }
    
    public void setPuntuacion1Cliente(int puntuacion1){
        this.puntuacion1 = puntuacion1;
    }

    public int getPuntuacion2() {
        return puntuacion2;
    }

    public void setPuntuacion2(int puntuacion2) {
        this.puntuacion2 = this.puntuacion2 +  puntuacion2;
    }
    
    public void setPuntuacion2Cliente(int puntuacion2){
        this.puntuacion2 = puntuacion2;
    }

    public Pelota(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Ellipse2D getPelota() {
        return new Ellipse2D.Float(x, y, ANCHO, ALTO);
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
    
    

    public void mover(Rectangle limites, boolean raqueta1, boolean raqueta2) {
        if (raqueta1) {
            x = x + 10;
            dx = -dx;
        }
        if (raqueta2) {
            x = x - 10;
            dx = -dx;
        }

        x += dx;
        y += dy;
        if (x > limites.getMaxX() - 30) {
            dx = -dx;
            setPuntuacion1(1);
        }
        if (y > limites.getMaxY() - 30) {
            dy = -dy;
        }
        if (x < 0) {
            dx = -dx;
            setPuntuacion2(1);
            
        }
        if (y < 0) {
            dy = -dy;
        }
    }
}
