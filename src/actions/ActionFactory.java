package org.berandev.byterover;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ActionFactory {
    public static MenuItemAction[] newStructureTreeActions(StructureTree tree){
        MenuItemAction[] actions = {
        new SetDefaultTreeNodeAction(tree),
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

class SetDefaultTreeNodeAction extends MenuItemAction {
    private StructureTreeModel treeModel;
    private StructureTree structureTree;

    public SetDefaultTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.setDefault"));
        treeModel = (StructureTreeModel) tree.getModel();
        structureTree = tree;
        setSeparated(true);
    }

    public void actionUpdate(){
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(contextNode.isShowable());
        setEnabled(!treeModel.isDefault(contextNode));
        setHint(isEnabled() ? "" : ResourceLoader.getHint("currentlyDefault"));
    }

    public void actionPerformed(ActionEvent event){
        treeModel.setDefaultNode(structureTree.getContextNode());
    }
}

class RenameTreeNodeAction extends MenuItemAction {
    private StructureTree structureTree;

    public RenameTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.renameBase"));
        structureTree = tree;
    }

    public void actionUpdate(){
        switch (structureTree.getContextNode().getType()){
            case StructureTreeNode.PROJECT_NODE:
                setName(ResourceLoader.getString("action.renameProject"));
                break;
            case StructureTreeNode.GROUP_NODE:
                setName(ResourceLoader.getString("action.renameGroup"));
                break;
            case StructureTreeNode.PAGE_NODE:
                setName(ResourceLoader.getString("action.renamePage"));
                break;
            default:
                setName(ResourceLoader.getString("action.renameBase"));
        }
    }

    public void actionPerformed(ActionEvent event){   
        structureTree.startEditingAtPath(structureTree.getContextPath());
    }
}

class DeleteTreeNodeAction extends MenuItemAction {
    private StructureTreeModel treeModel;
    private StructureTree structureTree;

    public DeleteTreeNodeAction(StructureTree tree){
        super(ResourceLoader.getString("action.deleteBase"));
        treeModel = (StructureTreeModel) tree.getModel();
        structureTree = tree;
    }

    public void actionUpdate(){
        StructureTreeNode contextNode = structureTree.getContextNode();
        setVisible(!contextNode.isRoot());
        if (treeModel.isDefault(contextNode)){
            setHint(ResourceLoader.getHint("currentlyDefault"));
            setEnabled(false);
        }
        else if (!contextNode.isLeaf()){
            setHint(ResourceLoader.getHint("groupNotEmpty"));
            setEnabled(false);
        }
        else {
            setHint("");
            setEnabled(true);
        }
        switch (contextNode.getType()){
            case StructureTreeNode.GROUP_NODE:
                setName(ResourceLoader.getString("action.deleteGroup"));
                break;
            case StructureTreeNode.PAGE_NODE:
                setName(ResourceLoader.getString("action.deletePage"));
                break;
            default:
                setName(ResourceLoader.getString("action.deleteBase"));
        }
    }

    public void actionPerformed(ActionEvent event){
        StructureTreeNode contextNode = structureTree.getContextNode();
        if (treeModel.isCurrent(contextNode)){
            treeModel.setCurrentNode(treeModel.getDefaultNode());
            structureTree.setSelectionPath(treeModel.getCurrentPath());
        }
        treeModel.removeNodeFromParent(contextNode);
    }
}

