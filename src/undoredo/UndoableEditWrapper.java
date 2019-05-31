/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package undoredo;

import common.User;
import java.io.Serializable;
import javax.swing.undo.UndoableEdit;

/**
 *
 * @author nickz
 */
public class UndoableEditWrapper implements Serializable {

    private UndoableEdit edit;
    private User userTag;

    public UndoableEditWrapper(UndoableEdit edit, User userTag) {
        this.edit = edit;
        this.userTag = userTag;
    }

    public UndoableEdit getEdit() {
        return edit;
    }

    public User getUserTag() {
        return userTag;
    }

}
