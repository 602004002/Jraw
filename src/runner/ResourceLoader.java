/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import common.User;
import java.util.ArrayList;
import frontend.tools.DrawingTool;
import layer.LayerPreset;

/**
 *
 * @author nickz
 */
public class ResourceLoader implements Runnable {
    
    private final ArrayList<DrawingTool> dtOut;
    private final ArrayList<LayerPreset> lsOut;
    
    private static String DIR;
    private static String FOLDER;
    
    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            DIR = System.getenv("APPDATA");
            FOLDER = "\\Jraw\\";
        } else if (os.contains("linux")) {
            DIR = System.getProperty("user.home");
            FOLDER = "/Jraw/";
        }
    }
    
    public ResourceLoader(ArrayList<DrawingTool> out1, ArrayList<LayerPreset> out2) {
        this.dtOut = out1;
        this.lsOut = out2;
    }
    
    private static void loadTools() throws IOException {
        System.out.println("Begin tool load");
        //load preset files

    }
    
    private static void loadLayerPresets() throws IOException {
        System.out.println("Begin layer preset load");
        //load preset files
    }
    
    private static void loadUser() throws IOException {
        System.out.println("Begin User load");
        File f = new File(DIR + FOLDER + "localuser");
        if (!f.exists() || f.length() == 0) {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(System.getProperty("user.name"));
            pw.println(UUID.randomUUID());
            pw.flush();
            fw.flush();
            pw.close();
            fw.close();
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        String username = br.readLine();
        UUID uuid = UUID.fromString(br.readLine());
        
        User.setLocalUser(new User(username, uuid));
        User.getLocalUser().pointerInfo().setUserTag(User.getLocalUser());
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Resource Loader thread started");
            File f = new File(DIR + FOLDER);
            if (!f.exists()) {
                f.mkdir();
            }
            loadTools();
            loadLayerPresets();
            loadUser();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
