package org.berandev.byterover;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;

public class ProjectTreeModel extends DefaultTreeModel {
    private TreePath pageSelection;

    public ProjectTreeModel(TreeNode root){
        super(root);
    }

    public void setPageSelection(TreeNode[] path){
        setPageSelection(new TreePath(path));
    }

    public void setPageSelection(TreePath path){
        pageSelection = path;
    }

    public TreePath getPageSelection(){
        return pageSelection;
    }

    public PageModel getSelectedPage(){
        if (pageSelection == null){
            return new PageModel();
        }
        ProjectTreeNode selectedNode = (ProjectTreeNode) pageSelection.getLastPathComponent();
        return selectedNode.getPageModel();
    }
}
