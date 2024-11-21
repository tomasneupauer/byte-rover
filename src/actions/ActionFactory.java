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
        Action[] actions = {
            new RenameTreeNodeAction(tree),
            new DeleteTreeNodeAction(tree)
        };
        return actions;
    }

    public static Action newMenuAction(String name){
        return new AbstractAction(name){
            public void actionPerformed(ActionEvent event) {};
        };
    }
}

abstract class AbstractItemAction extends AbstractAction {
    public AbstractItemAction(){
        super();
        addPropertyChangeListener(new UpdateListener());
    }

    public abstract void actionUpdate();

    private class UpdateListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent event){
            if (event.getPropertyName().equals(ActionFactory.UPDATE_ACTION)){
                actionUpdate();
            }
        }
    }
}

class RenameTreeNodeAction extends AbstractItemAction {
    private StructureTree structureTree;
    private String baseName;

    public RenameTreeNodeAction(StructureTree tree){
        super();
        structureTree = tree;
        baseName = ResourceLoader.getString("action.base.rename");
    }

    public void actionUpdate(){
        if (structureTree.getSelectionCount() > 0){
            String actionType = structureTree.getSelectedNodeType();
            putValue(NAME, baseName + " " + actionType);
            setEnabled(true);
        }
        else {
            putValue(NAME, baseName);
            setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent event){
        structureTree.startEditingAtPath(structureTree.getSelectionPath());
    }
}

class DeleteTreeNodeAction extends AbstractItemAction {
    private StructureTree structureTree;
    private String baseName;

    public DeleteTreeNodeAction(StructureTree tree){
        super();
        structureTree = tree;
        baseName = ResourceLoader.getString("action.base.delete");
    }

    public void actionUpdate(){
        if (structureTree.getSelectionCount() > 0){
            String actionType = structureTree.getSelectedNodeType();
            putValue(NAME, baseName + " " + actionType);
            setEnabled(true);
        }
        else {
            putValue(NAME, baseName);
            setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent event){
        
    }
}

class EditSelectedNodeAction extends AbstractItemAction {
    private StructureTree structureTree;

    public EditSelectedNodeAction(StructureTree tree){
        super();
        structureTree = tree;
    }

    public void actionUpdate(){
        setEnabled(structureTree.getSelectionCount() > 0);
    }

    public void actionPerformed(ActionEvent event){}
}
