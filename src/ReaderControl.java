package org.berandev.byterover;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class ReaderControl {
    private ProjectModel projectModel;
    private ReaderView readerView;
    private StructureTree projectTree;
    private JEditorPane contentEditorPane;

    public ReaderControl(){
        readerView = new ReaderView();
        projectTree = (StructureTree) readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
    }

    public void initControl(ProjectModel model){
        projectModel = model;
        projectTree.bindProjectModel(model);
        projectTree.buildProjectTree();
        projectTree.disableRootCollapse();
        projectTree.setupCellEditor();
        projectTree.addMouseListener(new TreeSelectionMouseListener());
        //projectTree.startEditingAtPath(projectTree.getPathForRow(2));
        System.out.println(projectTree.isEditable());
        contentEditorPane.setEditable(false);
        updatePageSelection();
    }

    public ReaderView getView(){
        return readerView;
    }

    private void updatePageSelection(){
        contentEditorPane.setContentType(projectModel.getSelectedContentType());
        contentEditorPane.setText(projectModel.readSelectedContent());
    }

    private class TreeSelectionMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                TreePath eventPath = projectTree.getClosestPathForLocation(event.getX(), event.getY());
                DefaultMutableTreeNode eventNode = (DefaultMutableTreeNode) eventPath.getLastPathComponent();
                String eventNodeName = (String) eventNode.getUserObject();
                if (projectModel.hasPage(eventNodeName)){
                    projectModel.setSelectedPageName(eventNodeName);
                    updatePageSelection();
                }
            }
        }
    }
}

