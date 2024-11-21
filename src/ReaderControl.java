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
import javax.swing.JMenuBar;

//import javax.swing.InputMap;
//import javax.swing.ActionMap;
//import javax.swing.JComponent;
//import javax.swing.KeyStroke;

public class ReaderControl {
    private ReaderView readerView;
    private StructureTree projectTree;
    private ProjectTreeModel treeModel;
    private JEditorPane contentEditorPane;
    private JMenuBar menuBar;

    public ReaderControl(AppView appView){
        menuBar = new JMenuBar();
        appView.setJMenuBar(menuBar);
        readerView = new ReaderView();
        projectTree = (StructureTree) readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
    }

    public void initControl(ProjectTreeModel model){
        treeModel = model;
        treeModel.setCurrentPath(treeModel.getDefaultPath());
        projectTree.setModel(treeModel);
        projectTree.setSelectionPath(model.getCurrentPath());
        projectTree.addMouseListener(new TreeSelectionMouseListener());

        Action[] treeActions = ActionFactory.newStructureTreeActions(projectTree);
        Action editMenuAction = ActionFactory.newMenuAction(ResourceLoader.getString("action.menu.edit"));
        projectTree.setContextMenu(MenuFactory.newPopupMenu(treeActions));
        menuBar.add(MenuFactory.newMenu(treeActions, editMenuAction));

        contentEditorPane.setEditable(false);
        updatePageSelection();
    }

    public ReaderView getView(){
        return readerView;
    }

    private void updatePageSelection(){
        PageModel selectedPage = treeModel.getCurrentPage();
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
                treeModel.setCurrentPath(eventPath);
                updatePageSelection();
            }
        }
    }
}

