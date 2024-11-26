package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ActionModel {
    private Map<Object, MenuItemAction> actionMap;

    public ActionModel(){
        actionMap = new HashMap<Object, MenuItemAction>();
    }

    public MenuItemAction getAction(Object key){
        return actionMap.get(key);
    }

    public void putAction(Object key, MenuItemAction action){
        actionMap.put(key, action);
    }

    public void newStructureTreeActions(StructureTree tree){
        putAction(ActionKey.STRUCTURE_TREE_MENU, new StructureTreeMenuAction());
        putAction(ActionKey.CREATE_PAGE_NODE, new CreatePageNodeAction(tree));
        putAction(ActionKey.CREATE_GROUP_NODE, new CreateGroupNodeAction(tree));
        putAction(ActionKey.SET_DEFAULT_TREE_NODE, new SetDefaultTreeNodeAction(tree));
        putAction(ActionKey.RENAME_TREE_NODE, new RenameTreeNodeAction(tree));
        putAction(ActionKey.DELETE_TREE_NODE, new DeleteTreeNodeAction(tree));
    }
}

