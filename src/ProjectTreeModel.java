package org.berandev.byterover;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;

public class ProjectTreeModel extends DefaultTreeModel {
    private TreePath defaultSelection;

    public ProjectTreeModel(TreeNode root){
        super(root);
        defaultSelection = new TreePath(root);
    }

    public void setDefaultSelection(TreeNode[] path){
        defaultSelection = new TreePath(path);
    }

    public TreePath getDefaultSelection(){
        return defaultSelection;
    }
}
