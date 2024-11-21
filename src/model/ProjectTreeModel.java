package org.berandev.byterover;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeNode;

public class ProjectTreeModel extends DefaultTreeModel {
    private TreePath defaultPath;
    private TreePath currentPath;

    public ProjectTreeModel(TreeNode root){
        super(root);
    }

    public void setDefaultPath(TreeNode[] path){
        setDefaultPath(new TreePath(path));
    }

    public void setDefaultPath(TreePath path){
        defaultPath = path;
    }

    public void setCurrentPath(TreePath path){
        currentPath = path;
    }

    public TreePath getDefaultPath(){
        return defaultPath;
    }

    public TreePath getCurrentPath(){
        return currentPath;
    }

    public PageModel getCurrentPage(){
        if (currentPath == null){
            return new PageModel();
        }
        Object currentNode = currentPath.getLastPathComponent();
        return ((ProjectTreeNode) currentNode).getPageModel();
    }
}
