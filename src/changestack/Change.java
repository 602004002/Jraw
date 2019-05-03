package changestack;

import common.User;

/**
 *
 * @author nickz
 */
public abstract class Change {
    private User userTag;
    private String name;
    
    protected Change(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
