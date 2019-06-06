/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import frontend.MainView;
import java.util.ArrayList;
import frontend.tools.DrawingTool;
import java.util.logging.Level;
import java.util.logging.Logger;
import layer.LayerPreset;

/**
 *
 * @author nickz
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //Loading screen
        ArrayList<DrawingTool> dt = new ArrayList<>();
        ArrayList<LayerPreset> lp = new ArrayList<>();
        Thread t1 = new Thread(new ResourceLoader(dt, lp));
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Main program thread started");
            MainView mv = new MainView();
            mv.finishInit();
            mv.setVisible(true);
        });
        t2.start();
        t1.start();
    }
}
