package org.berandev.byterover;

import java.awt.event.ActionEvent;

public class StructureTreeMenuAction extends MenuItemAction {
    public StructureTreeMenuAction(){
        super(ResourceLoader.getString("action.structureTreeMenu"));
        setSubActionKeys(new Object[]{
            ActionKey.CREATE_PAGE_NODE,
            ActionKey.CREATE_GROUP_NODE,
            ActionKey.SET_DEFAULT_TREE_NODE,
            ActionKey.RENAME_TREE_NODE,
            ActionKey.DELETE_TREE_NODE
        });
    }

    public void actionUpdate(){}
    public void actionPerformed(ActionEvent event){}
}

