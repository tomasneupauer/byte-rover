package org.berandev.byterover;

import javax.swing.AbstractAction;

public abstract class MenuItemAction extends AbstractAction {
    private Object[] subActionKeys;
    private boolean separated;
    private boolean visible;

    public MenuItemAction(String name){
        super(name);
        subActionKeys = new Object[0];
        separated = false;
        visible = true;
    }

    public Object[] getSubActionKeys(){
        return subActionKeys;
    }

    public boolean isSeparated(){
        return separated;
    }

    public boolean isVisible(){
        return visible;
    }

    public boolean isMenu(){
        return subActionKeys.length != 0;
    }

    public void setSubActionKeys(Object[] keys){
        subActionKeys = keys;
    }

    public void setSeparated(boolean value){
        separated = value;
    }

    public void setVisible(boolean value){
        visible = value;
    }

    public void setHint(String hint){
        putValue(SHORT_DESCRIPTION, hint);
    }

    public void setName(String name){
        putValue(NAME, name);
    }

    public abstract void actionUpdate();
}

