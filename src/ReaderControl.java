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
    private ProjectTreeModel projectTreeModel;
    private JEditorPane contentEditorPane;

    public ReaderControl(){
        readerView = new ReaderView();
        projectTree = (StructureTree) readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
    }

    public void initControl(ProjectTreeModel model){
        projectTreeModel = model;
        projectTree.setModel(model);
        projectTree.setSelectionPath(model.getPageSelection());
        projectTree.setEditable(true);
        projectTree.addMouseListener(new TreeSelectionMouseListener());
        projectTree.disableRootCollapse();
        projectTree.disableEditOnClick();

        contentEditorPane.setEditable(false);
        updatePageSelection();
    }

    public ReaderView getView(){
        return readerView;
    }

    private void updatePageSelection(){
        PageModel selectedPage = projectTreeModel.getSelectedPage();
        contentEditorPane.setContentType(selectedPage.getContentType());
        contentEditorPane.setText(selectedPage.readContent());
    }

    private class TreeSelectionMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){
            if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1){
                TreePath eventPath = projectTree.getClosestPathForLocation(event.getX(), event.getY());
                ProjectTreeNode eventNode = (ProjectTreeNode) eventPath.getLastPathComponent();
                if (eventNode.isPage()){
                    projectTreeModel.setPageSelection(eventPath);
                    updatePageSelection();
                }
            }
        }
    }
}

