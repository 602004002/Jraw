/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import frontend.MainView;
import layer.LayerSettings;
import frontend.Model;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import frontend.tools.DrawingTool;

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
        ArrayList<LayerSettings> ls = new ArrayList<>();
        Thread rl = new Thread(new ResourceLoader(dt, ls), "ResourceLoader");
        Thread mw = new Thread(() -> {
            System.out.println("Main program thread started");
            try {
                rl.join();
                Model m = new Model();
                MainView mv = new MainView(m);
                mv.finishInit();
                mv.setVisible(true);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Failed to join ResourceLoader thread");
            }
        }, "Main Window");
        mw.start();
        rl.start();
    }
}
