package org.berandev.byterover;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class ReaderControl {
    private ReaderView readerView;
    private StructureTree projectTree;
    private JEditorPane contentEditorPane;

    public ReaderControl(){
        readerView = new ReaderView();
        projectTree = (StructureTree) readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
    }

    public void initControl(ProjectTreeModel model){
        projectTree.setModel(model);
        projectTree.setSelectionPath(model.getDefaultSelection());
        projectTree.addMouseListener(new TreeSelectionMouseListener());

        //projectTree.bindProjectModel(model);
        //projectTree.buildProjectTree();
        //projectTree.disableRootCollapse();
        //projectTree.setupCellEditor();
        //projectTree.startEditingAtPath(projectTree.getPathForRow(2));
        //System.out.println(projectTree.isEditable());
        contentEditorPane.setEditable(false);
        //updatePageSelection();
    }

    public ReaderView getView(){
        return readerView;
    }

    private void updatePageSelection(){
        TreePath selectionPath = projectTree.getSelectionPath();
        ProjectTreeNode selectedNode = (ProjectTreeNode) selectionPath.getLastPathComponent();
        if (selectedNode.isLeaf()){
            PageModel selectedPage = (PageModel) selectedNode.getUserObject();
            contentEditorPane.setContentType(selectedPage.getContentType());
            contentEditorPane.setText(selectedPage.readContent());
        }
    }

    private class TreeSelectionMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                TreePath eventPath = projectTree.getClosestPathForLocation(event.getX(), event.getY());
                ProjectTreeNode eventNode = (ProjectTreeNode) eventPath.getLastPathComponent();
                if (eventNode.isLeaf()){
                    updatePageSelection();
                }
            }
        }
    }
}

