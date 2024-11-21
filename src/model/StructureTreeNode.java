package org.berandev.byterover;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public interface StructureTreeNode extends MutableTreeNode {
    public String getType();
    public TreeNode[] getPath(); 
    public boolean isRoot();
    public boolean isLeaf();
    public boolean isShowable();
}

