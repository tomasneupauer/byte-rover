package org.berandev.byterover;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeModelEvent;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class StructureTree extends JTree {
    public void disableRootCollapse(){
        addTreeWillExpandListener(new RootCollapseListener());
    }

    public void setupCellEditor(){
        setEditable(true);
        setCellEditor(new DefaultTreeCellEditor(this, (DefaultTreeCellRenderer) getCellRenderer()){
            //@Override
            //public boolean isCellEditable(EventObject event){
            //    return event == null;
            //}
        });
    }

    private DefaultMutableTreeNode getLastPathNode(TreePath path){
        return (DefaultMutableTreeNode) path.getLastPathComponent();
    }

    private DefaultMutableTreeNode getTreeRootNode(){
        return (DefaultMutableTreeNode) getModel().getRoot();
    }

    private class ProjectModelUpdater implements TreeModelListener {
        public void treeNodesChanged(TreeModelEvent event){}

        public void treeNodesInserted(TreeModelEvent event){}

        public void treeNodesRemoved(TreeModelEvent event){}

        public void treeStructureChanged(TreeModelEvent event){}
    }

    private class RootCollapseListener implements TreeWillExpandListener {
        public void treeWillExpand(TreeExpansionEvent event){}

        public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
            if (getLastPathNode(event.getPath()).equals(getTreeRootNode())){
                throw new ExpandVetoException(event);
            }
        }
    }
}

