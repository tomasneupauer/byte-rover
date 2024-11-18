package org.berandev.byterover;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.Action;

import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class ReaderControl {
    private ReaderView readerView;
    private StructureTree projectTree;
    private ProjectTreeModel projectTreeModel;
    private JEditorPane contentEditorPane;
    private Action renameAction;

    public ReaderControl(){
        readerView = new ReaderView();
        projectTree = (StructureTree) readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
    }

    public void initControl(ProjectTreeModel model){
        projectTreeModel = model;
        projectTree.setModel(model);
        projectTree.setSelectionPath(model.getPageSelection());
        projectTree.addMouseListener(new TreeSelectionMouseListener());

        Action[] treeActions = ActionsFactory.newStructureTreeActions(projectTree);
        projectTree.setContextMenu(MenuFactory.newPopupMenu(treeActions));

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
            if (event.getButton() != MouseEvent.BUTTON1 || event.getClickCount() != 2){
                return;
            }
            TreePath eventPath = projectTree.getPathForMouseEvent(event);
            if (eventPath == null){
                return;
            }
            ProjectTreeNode eventNode = (ProjectTreeNode) eventPath.getLastPathComponent();
            if (eventNode.isPage()){
                projectTreeModel.setPageSelection(eventPath);
                updatePageSelection();
            }
        }
    }
}

