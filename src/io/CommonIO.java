/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import common.SessionModel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import layer.RasterLayer;
import layer.VectorLayer;

/**
 *
 * @author nickz
 */
public class CommonIO {

    public static BufferedImage render(SessionModel sm) {//render to a single image
        Dimension size = sm.getSize();
        BufferedImage ri = new BufferedImage(size.width, size.width,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = ri.getGraphics();
        sm.hierarchy.forEach((JComponent layer) ->{
            if (layer instanceof RasterLayer) {
                g.drawImage(((RasterLayer) layer).getImg(), 0, 0, layer);
            }
            if (layer instanceof VectorLayer) {
                throw new UnsupportedOperationException("VectorLayer is not implemented yet.");
            }
        });
        g.dispose();
        return ri;
    }

    public static void export(SessionModel sm, File path, ImageType type) throws IOException {
        BufferedImage bi = render(sm);
        System.out.println(type + "");
        ImageIO.write(bi, type + "", path);
    }

    public static void saveProprieteryFormat(SessionModel sm, File path) throws IOException, FileExistsException {
        System.out.println(path);
        if (path.exists()) {
            throw new FileExistsException("File exists");
        }
        
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
