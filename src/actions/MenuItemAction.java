package org.berandev.byterover;

import javax.swing.AbstractAction;

public abstract class MenuItemAction extends AbstractAction {
    private Object[] subActionKeys;
    private boolean visible;
    private boolean separated;

    public MenuItemAction(String name){
        super(name);
        subActionKeys = new Object[0];
        visible = true;
        separated = false;
    }

    public Object[] getSubActionKeys(){
        return subActionKeys;
    }

    public boolean isVisible(){
        return visible;
    }

    public boolean isSeparated(){
        return separated;
    }

    public void setSubActionKeys(Object[] keys){
        subActionKeys = keys;
    }

    public void setVisible(boolean value){
        visible = value;
    }

    public void setSeparated(boolean value){
        separated = value;
    }

    public void setHint(String hint){
        putValue(SHORT_DESCRIPTION, hint);
    }

    public void setName(String name){
        putValue(NAME, name);
    }

    public abstract void actionUpdate();
}

