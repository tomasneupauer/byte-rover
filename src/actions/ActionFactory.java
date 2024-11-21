package org.berandev.byterover;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ActionFactory {
    public static final String INVOKE_UPDATE = "INVOKE_UPDATE";
    public static final String VISIBLE = "VISIBLE";

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
        putValue(ActionFactory.VISIBLE, true);
        addPropertyChangeListener(new UpdateListener());
    }

    public abstract void actionUpdate();

    private class UpdateListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent event){
            if (event.getPropertyName().equals(ActionFactory.INVOKE_UPDATE)){
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
            putValue(NAME, baseName + " " + structureTree.getSelectedNode().getType());
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
    private StructureTreeModel treeModel;
    private String baseName;

    public DeleteTreeNodeAction(StructureTree tree){
        super();
        structureTree = tree;
        treeModel = (StructureTreeModel) tree.getModel();
        baseName = ResourceLoader.getString("action.base.delete");
    }

    public void actionUpdate(){
        if (structureTree.getSelectionCount() > 0){
            StructureTreeNode selectedNode = structureTree.getSelectedNode();
            putValue(ActionFactory.VISIBLE, selectedNode.isRoot());
            putValue(NAME, baseName + " " + selectedNode.getType());
            setEnabled(selectedNode.isLeaf() && !treeModel.isDefault(selectedNode));
        }
        else {
            putValue(NAME, baseName);
            setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeNode selectedNode = structureTree.getSelectedNode();
        if (treeModel.isCurrent(selectedNode)){
            treeModel.setCurrentNode(treeModel.getDefaultNode());
            structureTree.setSelectionPath(treeModel.getCurrentPath());
        }
        treeModel.removeNodeFromParent(selectedNode);
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
