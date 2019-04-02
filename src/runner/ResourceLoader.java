/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import layer.LayerSettings;
import java.util.ArrayList;
import tools.DrawingTool;

/**
 *
 * @author nickz
 */
public class ResourceLoader implements Runnable {

    private final ArrayList<DrawingTool> dtOut;
    private final ArrayList<LayerSettings> lsOut;

    public ResourceLoader(ArrayList<DrawingTool> out1, ArrayList<LayerSettings> out2) {
        this.dtOut = out1;
        this.lsOut = out2;
    }

    private static void loadTools() {
        System.out.println("Begin tool load");
        //load preset files
    }

    private static void loadLayerPresets() {
        System.out.println("Begin layer preset load");
        //load preset files
    }

    @Override
    public void run() {
        System.out.println("Resource Loader thread started");
        loadTools();
        loadLayerPresets();
    }
}
