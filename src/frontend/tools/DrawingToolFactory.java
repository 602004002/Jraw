/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.tools;

/**
 *
 * @author nickz
 */
public class DrawingToolFactory {

    public static DrawingTool.AbstractBuilder getBuilder(String type) {
        type = type.toLowerCase();
        switch (type) {
            case "pentool":
            case "pen":
                return new PencilTool.Builder();
        }
        return null;
    }

}
