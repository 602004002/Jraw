/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import frontend.tools.DrawingTool;
import input.PointerInfo;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author nickz
 */
public class User implements Serializable {
    
    private static final long serialVersionUID = 10L;

    public static User localUser;

    private String username;
    private UUID uuid;
    private transient DrawingTool drawingTool;
    private transient PointerInfo pointerInfo;

    public User(String username, UUID uuid) {
        this.username = username;
        this.uuid = uuid;
        this.pointerInfo = new PointerInfo();
    }

    public PointerInfo pointerInfo() {
        return this.pointerInfo;
    }

    public DrawingTool drawingTool() {
        return this.drawingTool;
    }

    public void setDrawingTool(DrawingTool drawingTool) {
        this.drawingTool = drawingTool;
    }

    public String username() {
        return this.username;
    }
    
    public UUID uuid() {
        return this.uuid;
    }
    
    @Override
    public String toString() {
        return this.username + " " + this.uuid;
    }

}
