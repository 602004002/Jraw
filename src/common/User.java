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

    private static final long serialVersionUID = 11L;

    private static User localUser;

    public static User getLocalUser() {
        return localUser;
    }

    public static void setLocalUser(User aLocalUser) {
        localUser = aLocalUser;
    }

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

    public void setPointerInfo(PointerInfo pointerInfo) {
        this.pointerInfo = pointerInfo;
    }

    public DrawingTool drawingTool() {
        return this.drawingTool;
    }

    public void setDrawingTool(DrawingTool drawingTool) {
        this.drawingTool = drawingTool;
        this.pointerInfo.setDiameter(drawingTool.diameter());
    }

    public String username() {
        return this.username;
    }

    public UUID uuid() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        return uuid.equals(((User) o).uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return this.username + " " + this.uuid;
    }

}
