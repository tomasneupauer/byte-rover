package org.berandev.byterover;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.event.TreeExpansionEvent;
import java.util.HashMap;
import java.util.Map;

public class TreeHandler {
    public static DefaultTreeModel buildProjectTree(ProjectModel model){
        Map<String, DefaultMutableTreeNode> nodes = new HashMap<>();
        String[] nodeNames = model.getTreeNodeNames();
        for (String nodeName : nodeNames){
            nodes.put(nodeName, new DefaultMutableTreeNode(nodeName));
        }
        for (String nodeName : nodeNames){
            String nodeParentName = model.getTreeNodeParentName(nodeName);
            if (!nodeName.equals(model.getTreeRootName())){
                nodes.get(nodeParentName).add(nodes.get(nodeName));
            }
        }
        return new DefaultTreeModel(nodes.get(model.getTreeRootName()));
    }

    public static void disableRootCollapse(JTree tree){
        tree.addTreeWillExpandListener(new TreeWillExpandListener(){
            public void treeWillExpand(TreeExpansionEvent event){}

            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode eventNode = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
                if (eventNode.equals(rootNode)){
                    throw new ExpandVetoException(event);
                }
            }
        });
    }
}

