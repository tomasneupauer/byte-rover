package org.berandev.byterover;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ActionFactory implements ActionKeys {
public static Action[] newStructureTreeActions(StructureTree tree){
    Action[] actions = {
        new RenameTreeNodeAction(tree),
        new DeleteTreeNodeAction(tree),
        new SetDefaultTreeNodeAction(tree)
        };
        return actions;
    }

    public static Action newMenuAction(String name){
        return new AbstractAction(name){
            public void actionPerformed(ActionEvent event) {};
        };
    }
}

interface ActionKeys {
    String INVOKE_UPDATE = "INVOKE_UPDATE";
    String VISIBLE = "VISIBLE";
    String SEPARATED = "SEPARATED";
}

abstract class AbstractItemAction extends AbstractAction implements ActionKeys {
    public AbstractItemAction(){
        putValue(VISIBLE, true);
        putValue(SEPARATED, false);
        addPropertyChangeListener(new UpdateListener());
    }

    public abstract void actionUpdate();

    private class UpdateListener implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent event){
            if (event.getPropertyName().equals(INVOKE_UPDATE)){
                actionUpdate();
            }
        }
    }
}

class RenameTreeNodeAction extends AbstractItemAction {
    private StructureTree structureTree;

    public RenameTreeNodeAction(StructureTree tree){
        structureTree = tree;
        putValue(NAME, ResourceLoader.getString("action.renameBase"));
    }

    public void actionUpdate(){
        if (structureTree.getSelectionCount() > 0){
            StructureTreeNode selectedNode = structureTree.getSelectedNode();
            setTypeName(selectedNode.getType());
            setEnabled(true);
        }
        else {
            putValue(NAME, ResourceLoader.getString("action.renameBase"));
            setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent event){
        structureTree.startEditingAtPath(structureTree.getSelectionPath());
    }

    private void setTypeName(int type){
        switch (type){
            case StructureTreeNode.PROJECT_NODE:
                putValue(NAME, ResourceLoader.getString("action.renameProject"));
                break;
            case StructureTreeNode.GROUP_NODE:
                putValue(NAME, ResourceLoader.getString("action.renameGroup"));
                break;
            case StructureTreeNode.PAGE_NODE:
                putValue(NAME, ResourceLoader.getString("action.renamePage"));
                break;
            default:
                putValue(NAME, ResourceLoader.getString("action.renameBase"));
        }
    }
}

class DeleteTreeNodeAction extends AbstractItemAction {
    private StructureTree structureTree;
    private StructureTreeModel treeModel;

    public DeleteTreeNodeAction(StructureTree tree){
        structureTree = tree;
        treeModel = (StructureTreeModel) tree.getModel();
        putValue(NAME, ResourceLoader.getString("action.deleteBase"));
        putValue(SEPARATED, true);
    }

    public void actionUpdate(){
        if (structureTree.getSelectionCount() > 0){
            StructureTreeNode selectedNode = structureTree.getSelectedNode();
            putValue(VISIBLE, !selectedNode.isRoot());
            setTypeName(selectedNode.getType());
            setState(selectedNode.isLeaf(), treeModel.isDefault(selectedNode));
        }
        else {
            putValue(NAME, ResourceLoader.getString("action.deleteBase"));
            putValue(SHORT_DESCRIPTION, "");
            putValue(VISIBLE, true);
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

    private void setTypeName(int type){
        switch (type){
            case StructureTreeNode.PROJECT_NODE:
                putValue(NAME, ResourceLoader.getString("action.deleteProject"));
                break;
            case StructureTreeNode.GROUP_NODE:
                putValue(NAME, ResourceLoader.getString("action.deleteGroup"));
                break;
            case StructureTreeNode.PAGE_NODE:
                putValue(NAME, ResourceLoader.getString("action.deletePage"));
                break;
            default:
                putValue(NAME, ResourceLoader.getString("action.deleteBase"));
        }
    }

    private void setState(boolean isLeaf, boolean isDefault){
        setEnabled(isLeaf && !isDefault);
        String description = "";
        if (!isLeaf){
            description = ResourceLoader.getHint("groupNotEmpty");
        }
        if (isDefault){
            description = ResourceLoader.getHint("currentlyDefault");
        }
        putValue(SHORT_DESCRIPTION, description);
    }
}

class SetDefaultTreeNodeAction extends AbstractItemAction {
    private StructureTree structureTree;
    private StructureTreeModel treeModel;

    public SetDefaultTreeNodeAction(StructureTree tree){
        structureTree = tree;
        treeModel = (StructureTreeModel) tree.getModel();
        putValue(NAME, ResourceLoader.getString("action.setDefault"));
    }

    public void actionUpdate(){
        if (structureTree.getSelectionCount() > 0){
            StructureTreeNode selectedNode = structureTree.getSelectedNode();
            putValue(VISIBLE, selectedNode.isShowable());
            setState(!treeModel.isDefault(selectedNode));
        }
        else {
            putValue(SHORT_DESCRIPTION, "");
            putValue(VISIBLE, true);
            setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeNode selectedNode = structureTree.getSelectedNode();
        treeModel.setDefaultNode(selectedNode);
    }

    private void setState(boolean enabled){
        setEnabled(enabled);
        if (!enabled){
            putValue(SHORT_DESCRIPTION, ResourceLoader.getHint("currentlyDefault"));
        }
        else {
            putValue(SHORT_DESCRIPTION, "");
        }
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
