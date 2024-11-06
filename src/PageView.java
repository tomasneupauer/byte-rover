package org.berandev.byterover;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

public class PageView extends JPanel{
    private ProjectModel projectModel;

    private JPanel controlPanel;
    private JTree projectTree;
    
    private JScrollPane circuitScrollPane;
    private JPanel circuitPanel;

    private JScrollPane contentScrollPane;
    private JEditorPane contentEditorPane;

    public PageView (ProjectModel projectModel){
        this.projectModel = projectModel;
        setLayout(new GridBagLayout());

        // WEST
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        add(controlPanel, new Constraints(0, 0));

        projectTree = new JTree(buildProjectTree());
        controlPanel.add(projectTree, new Constraints(0, 0));

        // CENTER
        circuitPanel = new JPanel();
        circuitPanel.setLayout(null);

        circuitScrollPane = new JScrollPane(circuitPanel);
        add(circuitScrollPane, new Constraints(1, 0));

        // EAST
        contentEditorPane = new JEditorPane();
        contentEditorPane.setContentType(projectModel.getSelectedPageType());
        contentEditorPane.setText(projectModel.getSelectedPageContent());
        contentEditorPane.setEditable(false);

        contentScrollPane = new JScrollPane(contentEditorPane);
        add(contentScrollPane, new Constraints(2, 0));
    }

    private TreeModel buildProjectTree(){
        Map<String, DefaultMutableTreeNode> treeNodes = new HashMap<String, DefaultMutableTreeNode>();
        String treeRootName = projectModel.getTreeRootName();
        treeNodes.put(treeRootName, new DefaultMutableTreeNode(treeRootName));
        TreeModel projectTreeModel = new DefaultTreeModel(treeNodes.get(treeRootName));
        String[] treeNodeNames = projectModel.getTreeNodeNames();
        for (String treeNodeName : treeNodeNames){
            treeNodes.put(treeNodeName, new DefaultMutableTreeNode(treeNodeName));
        }
        for (String treeNodeName : treeNodeNames){
            String treeNodeParentName = projectModel.getTreeNodeParentName(treeNodeName);
            treeNodes.get(treeNodeParentName).add(treeNodes.get(treeNodeName));
        }
        return projectTreeModel;
    }
}

