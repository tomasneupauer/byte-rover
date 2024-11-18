package org.berandev.byterover;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ActionFactory {
    public static final String UPDATE_ACTION = "UPDATE_ACTION";
    public static final String PROJECT_NODE = ResourceLoader.getString("action.type.project");
    public static final String GROUP_NODE = ResourceLoader.getString("action.type.group");
    public static final String PAGE_NODE = ResourceLoader.getString("action.type.page");

    public static Action[] newStructureTreeActions(StructureTree tree){
        Action[] actions = {new RenameTreeNodeAction(tree)};
        return actions;
    }
}

abstract class AbstractMenuAction extends AbstractAction {
    public static final String BASE_NAME = "BASE_NAME";
    public static final String TYPE_NAME = "TYPE_NAME";

    public AbstractMenuAction(String baseName){
        super();
        putValue(BASE_NAME, baseName);
        addPropertyChangeListener(new UpdateListener());
    }

    public abstract void actionUpdate();

    private class UpdateListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent event){
            if (event.getPropertyName().equals(ActionFactory.UPDATE_ACTION)){
                actionUpdate();
                putValue(NAME, getValue(BASE_NAME) + " " + getValue(TYPE_NAME));
            }
        }
    }
}

class RenameTreeNodeAction extends AbstractMenuAction {
    private StructureTree structureTree;

    public RenameTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.base.rename"));
        structureTree = tree;
    }

    public void actionUpdate(){
        String actionType = structureTree.getSelectedNodeType();
        putValue(TYPE_NAME, actionType);
        setEnabled(actionType != ActionFactory.PROJECT_NODE);
    }

    public void actionPerformed(ActionEvent event){
        structureTree.startEditingAtPath(structureTree.getSelectionPath());
    }
}

