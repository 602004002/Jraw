/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import common.SessionModel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author nickz
 */
public class CommonIO {
    
    public enum ImageType {
        BMP, JPEG, PNG, TIFF, Targa, PSD
    }
    private SessionModel s;
    
    public Image render() {//render to a single image
        return null;
        
    }
    
    public void export(ImageType type) {//change to enum for formats

    }
    
    public Image importImage(String path, ImageType type) throws IOException {
        BufferedImage ret = null;
        switch (type) {
            case BMP:
            case JPEG:
            case PNG:
                ret = ImageIO.read(new File(path));
                break;
            case TIFF:
            case Targa:
            case PSD:
            default:
            
        }
        
        return ret;
    }
}
