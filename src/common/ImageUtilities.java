/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author nickz
 */
public class ImageUtilities {

    public static BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
        Image scaled = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
