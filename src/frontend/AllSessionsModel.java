package frontend;

import frontend.layerdisplay.LayerSubstrate;
import common.SessionModel;
import java.util.ArrayList;

public class AllSessionsModel {

    public AllSessionsModel() {
        this.sessionList = new ArrayList<>();
        this.substrateList = new ArrayList<>();
    }

    private final ArrayList<SessionModel> sessionList; //this is the absolute determiner
    private final ArrayList<LayerSubstrate> substrateList;

    private MainViewController mvc;

    public void setMainViewController(MainViewController mvc) {
        this.mvc = mvc;
    }

    public void add(SessionModel session) {
        if (session == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        this.sessionList.add(session);
        this.substrateList.add(new LayerSubstrate(session));
        mvc.updateTabs();
    }

    public SessionModel getSessionModel(int index) {
        return this.sessionList.get(index);
    }

    public LayerSubstrate getSubstrate(int index) {
        return this.substrateList.get(index);
    }

    public SessionModel getSessionModel(LayerSubstrate substrate) {
        if (substrate == null) {
            return null;
        }
        return this.sessionList.get(this.substrateList.indexOf(substrate));
    }

    public LayerSubstrate getSubstrate(SessionModel session) {
        if (session == null) {
            return null;
        }
        return this.substrateList.get(this.sessionList.indexOf(session));
    }

    /**
     * Removes both session and substrate at the specified index
     *
     * @param index
     */
    public void remove(int index) {
        this.sessionList.remove(index);
        this.substrateList.remove(index);
        this.mvc.updateTabs();
        System.gc();
    }

    /**
     * Removes specified session and associated substrate
     *
     * @param session
     */
    public void remove(SessionModel session) {
        this.remove(this.sessionList.indexOf(session));
    }

    /**
     * Removes specified substrate and associated session.
     *
     * @param substrate Substrate to remove
     */
    public void remove(LayerSubstrate substrate) {
        this.remove(this.substrateList.indexOf(substrate));
    }

    public int size() {
        assert (sessionList.size() == substrateList.size());
        return this.sessionList.size();
    }

    public int indexOf(SessionModel session) {
        if (session == null) {
            return -1;
        }
        for (int i = 0; i < this.sessionList.size(); i++) {
            if (this.sessionList.get(i).uuid().equals(session.uuid())) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(LayerSubstrate substrate) {
        if (substrate == null) {
            return -1;
        }
        return this.substrateList.indexOf(substrate);
    }

    public boolean contains(SessionModel sm) {
        return this.indexOf(sm) >= 0;
    }

    public void clear() {
        this.sessionList.clear();
        this.substrateList.clear();
    }

    @Override
    public String toString() {
        return this.sessionList.toString() + "\n" + this.substrateList.toString();
    }
}
