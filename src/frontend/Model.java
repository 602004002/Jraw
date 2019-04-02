package frontend;

import common.SessionModel;
import java.util.ArrayList;

public class Model {

    public Model() {
        this.sessionList = new ArrayList<>();
        this.substrateList = new ArrayList<>();
    }

    private final ArrayList<SessionModel> sessionList; //this is the absolute determiner
    private final ArrayList<LayerSubstrate> substrateList;

    public void add(SessionModel session) {
        this.sessionList.add(session);
        this.substrateList.add(new LayerSubstrate(session));
    }

    public SessionModel getSessionModel(int index) {
        return this.sessionList.get(index);
    }

    public SessionModel getSessionModel(LayerSubstrate substrate) {
        if (substrate == null) {
            return null;
        }
        return this.sessionList.get(this.substrateList.indexOf(substrate));
    }

    public LayerSubstrate getSubstrate(int index) {
        return this.substrateList.get(index);
    }

    public LayerSubstrate getSubstrate(SessionModel session) {
        if (session == null) {
            return null;
        }
        return this.substrateList.get(this.sessionList.indexOf(session));
    }

    public void remove(int index) {
        this.sessionList.remove(index);
        this.substrateList.remove(index);
    }

    public int size() {
        assert (sessionList.size() == substrateList.size());
        return this.sessionList.size();
    }

    public int indexOf(SessionModel session) {
        return this.sessionList.indexOf(session);
    }

    public int indexOf(LayerSubstrate substrate) {
        return this.substrateList.indexOf(substrate);
    }

    @Override
    public String toString() {
        return this.sessionList.toString() + "\n" + this.substrateList.toString();
    }
}
