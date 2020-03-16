/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.Vistas;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narut
 */
public class Movimiento extends Thread {

    Component tab;

    public Movimiento(Component tab) {
        this.tab = tab;
    }

    @Override
    public void run() {
        while (true) {
            tab.repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println("Hilo interrumpido");
            }

        }
    }
}
