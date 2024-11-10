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

public class ReaderControl {
    private ProjectModel projectModel;
    private ReaderView readerView;
    private JTree structureTree;
    private JEditorPane contentEditorPane;

    public ReaderControl(){
        readerView = new ReaderView();
        structureTree = readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
    }

    public void initControl(ProjectModel projectModel){
        this.projectModel = projectModel;
        structureTree.setModel(buildStructureTree());
        structureTree.addMouseListener(new TreeSelectionMouseListener());
        contentEditorPane.setEditable(false);
        updatePageSelection();
    }

    public ReaderView getView(){
        return readerView;
    }

    private class TreeSelectionMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                TreePath eventPath = structureTree.getPathForLocation(event.getX(), event.getY());
                DefaultMutableTreeNode eventNode = (DefaultMutableTreeNode) eventPath.getLastPathComponent();
                String eventNodeName = (String) eventNode.getUserObject();
                if (projectModel.hasPage(eventNodeName)){
                    projectModel.setSelectedPageName(eventNodeName);
                    updatePageSelection();
                }
            }
        }
    }

    private void updatePageSelection(){
        contentEditorPane.setContentType(projectModel.getSelectedContentType());
        contentEditorPane.setText(projectModel.readSelectedContent());
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

