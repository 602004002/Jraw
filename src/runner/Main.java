/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import frontend.MainView;
import java.util.ArrayList;
import frontend.tools.DrawingTool;
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
        java.awt.EventQueue.invokeLater(new ResourceLoader(dt, lp));
        java.awt.EventQueue.invokeLater(() -> {
            System.out.println("Main program thread started");
            MainView mv = new MainView();
            mv.finishInit();
            mv.setVisible(true);
        });
    }
}
