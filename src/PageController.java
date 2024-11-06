package org.berandev.byterover;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.HashMap;
import java.util.Map;

public class PageController{
    private ProjectModel projectModel;
    private PageView pageView;
    private JPanel controlPanel;
    private JPanel circuitPanel;
    private JEditorPane contentEditorPane;
    private JTree structureTree;

    public PageController (ProjectModel projectModel){
        this.projectModel = projectModel;
        pageView = new PageView();
        controlPanel = pageView.newControlPanel();
        circuitPanel = pageView.newCircuitPanel();
        contentEditorPane = pageView.newContentEditorPane();
        structureTree = pageView.newStructureTree();
        setupPageView();
    }

    public PageView getView(){
        return pageView;
    }

    private void setupPageView(){
        contentEditorPane.setContentType(projectModel.getSelectedPageType());
        contentEditorPane.setText(projectModel.readSelectedPage());
        contentEditorPane.setEditable(false);
        structureTree.setModel(buildStructureTree());
    }

    private DefaultTreeModel buildStructureTree(){
        Map<String, DefaultMutableTreeNode> treeNodes = new HashMap<>();
        String[] treeNodeNames = projectModel.getTreeNodeNames();
        for (String treeNodeName : treeNodeNames){
            treeNodes.put(treeNodeName, new DefaultMutableTreeNode(treeNodeName));
        }
        for (String treeNodeName : treeNodeNames){
            String treeNodeParentName = projectModel.getTreeNodeParentName(treeNodeName);
            if (!treeNodeName.equals(projectModel.getTreeRootName())){
                treeNodes.get(treeNodeParentName).add(treeNodes.get(treeNodeName));
            }
        }
        return new DefaultTreeModel(treeNodes.get(projectModel.getTreeRootName()));
    }
}

