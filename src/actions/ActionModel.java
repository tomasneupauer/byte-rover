package org.berandev.byterover;

import java.util.HashMap;
import java.util.Map;

public class ActionModel implements ActionKeys {
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

    public void newMenuActions(){
        putAction(FILE_MENU, new FileMenuAction());
        putAction(EDIT_MENU, new EditMenuAction());
        putAction(STRUCTURE_TREE_MENU, new StructureTreeMenuAction());
    }

    public void newProjectActions(AppControl control){
        putAction(OPEN_PROJECT, new OpenProjectAction(control));
    }

    public void newStructureTreeActions(StructureTree tree){
        putAction(CREATE_PAGE_NODE, new CreatePageNodeAction(tree));
        putAction(CREATE_GROUP_NODE, new CreateGroupNodeAction(tree));
        putAction(SET_DEFAULT_TREE_NODE, new SetDefaultTreeNodeAction(tree));
        putAction(RENAME_TREE_NODE, new RenameTreeNodeAction(tree));
        putAction(DELETE_TREE_NODE, new DeleteTreeNodeAction(tree));
    }
}

