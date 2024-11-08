package org.berandev.byterover;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class PageController{
    private ProjectModel projectModel;
    private PageView pageView;
    private JTree structureTree;
    private JEditorPane contentEditorPane;

    private class TreeSelectionMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                TreePath eventPath = structureTree.getPathForLocation(event.getX(), event.getY());
                DefaultMutableTreeNode eventNode = (DefaultMutableTreeNode) eventPath.getLastPathComponent();
                String eventNodeName = (String) eventNode.getUserObject();
                if (projectModel.isPage(eventNodeName)){
                    projectModel.selectPage(eventNodeName);
                    updatePageSelection();
                }
            }
        }
    }

    public PageController (ProjectModel projectModel){
        this.projectModel = projectModel;
        pageView = new PageView();
        structureTree = pageView.getStructureTree();
        contentEditorPane = pageView.getContentEditorPane();
        setupPageView();
    }

    public PageView getView(){
        return pageView;
    }

    private void setupPageView(){
        structureTree.setModel(buildStructureTree());
        structureTree.addMouseListener(new TreeSelectionMouseListener());
        contentEditorPane.setEditable(false);
        updatePageSelection();
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

    private void updatePageSelection(){
        contentEditorPane.setContentType(projectModel.getSelectedPageType());
        contentEditorPane.setText(projectModel.readSelectedPage());
    }
}

