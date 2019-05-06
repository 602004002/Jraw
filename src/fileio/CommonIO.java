/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import common.SessionModel;
import layer.DrawingLayer;
import layer.RasterLayer;
import layer.VectorLayer;

/**
 *
 * @author nickz
 */
public class CommonIO {

    private static File getLastPath() {
        String p = System.getProperty("lastUsedPath");
        if (p == null) {
            setLastPath("default");
            p = System.getProperty("lastUsedPath");
        }
        return new File(p);
    }

    private static void setLastPath(String path) {
        if (path.equals("default")) {
            path = System.getProperty("user.home");
        }
        System.setProperty("lastUsedPath", path);
    }

    private static boolean endsWithFileExtension(File file, FileNameExtensionFilter... fnef) {
        boolean r = false;
        String path = file.getPath();
        for (FileNameExtensionFilter f : fnef) {
            for (String ext : f.getExtensions()) {
                if (path.endsWith("." + ext)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static File showSaveDialog(JFrame parent, FileNameExtensionFilter... fnef) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(fnef[0]);
        for (FileNameExtensionFilter f : fnef) {
            jfc.addChoosableFileFilter(f);
        }
        jfc.setCurrentDirectory(CommonIO.getLastPath());
        int status = jfc.showSaveDialog(parent);
        if (status == JFileChooser.APPROVE_OPTION) {
            File theFile = jfc.getSelectedFile();
            if (!endsWithFileExtension(theFile, fnef)) {
                FileNameExtensionFilter ef = (FileNameExtensionFilter) jfc.getFileFilter();
                theFile = new File(theFile.getPath() + "." + ef.getExtensions()[0]);
            }
            String thePath = theFile.getAbsolutePath();
            setLastPath(thePath);
            return theFile;
        }
        return null;
    }

    public static File showSaveDialog(File prev, JFrame parent, FileNameExtensionFilter... fnef) {
        if (prev != null) {
            setLastPath(prev.getPath());
        }
        return showSaveDialog(parent, fnef);
    }

    public static File showOpenDialog(JFrame parent, FileNameExtensionFilter... fnef) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(fnef[0]);
        for (FileNameExtensionFilter f : fnef) {
            jfc.addChoosableFileFilter(f);
        }
        jfc.setCurrentDirectory(CommonIO.getLastPath());
        int status = jfc.showOpenDialog(parent);
        if (status == JFileChooser.APPROVE_OPTION) {
            File theFile = jfc.getSelectedFile();
            if (!endsWithFileExtension(theFile, fnef)) {
                FileNameExtensionFilter ef = (FileNameExtensionFilter) jfc.getFileFilter();
                theFile = new File(theFile.getPath() + "." + ef.getExtensions()[0]);
            }
            String thePath = theFile.getAbsolutePath();
            setLastPath(thePath);
            return theFile;
        }
        return null;
    }

    public static BufferedImage render(SessionModel sm, boolean transparency) {//render to a single image
        Dimension size = sm.size();
        int type = transparency ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
        BufferedImage ri = new BufferedImage(size.width, size.height, type);
        Graphics g = ri.getGraphics();
        sm.layerHierarchy.forEach((DrawingLayer layer) -> {
            if (layer instanceof RasterLayer && layer.isVisible()) {
                g.drawImage(((RasterLayer) layer).getRasterImage(), 0, 0, layer);
            }
            if (layer instanceof VectorLayer) {
                throw new UnsupportedOperationException("VectorLayer is not implemented yet.");
            }
        });
        g.dispose();
        return ri;
    }

    public static void export(SessionModel sm, File path, boolean overwrite) throws IOException, FileExistsException {
        if (path.exists() && !overwrite) {
            throw new FileExistsException("File exists");
        }
        String stringpath = path.getPath();
        int dot = stringpath.lastIndexOf('.');
        String type = stringpath.substring(dot + 1, stringpath.length());
        BufferedImage bi = render(sm, !type.equals("jpg"));
        ImageIO.write(bi, type, path);
    }

    public static void saveProprieteryFormat(SessionModel sm, File path, boolean overwrite) throws IOException, FileExistsException {
        if (path.exists() && !overwrite) {
            throw new FileExistsException("File exists");
        }
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sm);
        oos.flush();
        oos.close();
        fos.flush();
        fos.close();
    }

    public static SessionModel readProprieteryFormat(File path) throws IOException {
        SessionModel sm = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            sm = (SessionModel) ois.readObject();
            ois.close();
            fis.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
        return sm;
    }

    public static BufferedImage importImage(File path, ImageType type) throws IOException {
        BufferedImage ret = null;
        switch (type) {
            case JPEG:
            case PNG:
                System.out.println(path);
                ret = ImageIO.read(path);
                break;
            case GIF:
            case TIFF:
            case Targa:
            case PSD:
            default:

        }

        return ret;
    }
}
