package org.berandev.byterover;

import javax.swing.JEditorPane;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.Action;

//import javax.swing.InputMap;
//import javax.swing.ActionMap;
//import javax.swing.JComponent;
//import javax.swing.KeyStroke;

public class ReaderControl implements ActionListener {
    private AppControl appControl;
    private ReaderView readerView;
    private StructureTree structureTree;
    private StructureTreeModel treeModel;
    private JEditorPane contentEditorPane;

    public ReaderControl(AppControl control){
        appControl = control;
        readerView = new ReaderView();
        structureTree = (StructureTree) readerView.getStructureTree();
        contentEditorPane = readerView.getContentEditorPane();
        appControl.getActionModel().newStructureTreeActions(structureTree);
    }

    public void initControl(){
        treeModel = appControl.getProjectModel();
        treeModel.setCurrentNode(treeModel.getDefaultNode());
        treeModel.addCurrentNodeListener(this);
        structureTree.setModel(treeModel);
        structureTree.setSelectionPath(treeModel.getCurrentPath());

        MenuItemAction[] treeActions = ActionFactory.newStructureTreeActions(structureTree);
        Action editMenuAction = ActionFactory.newMenuAction(ResourceLoader.getString("action.menu.edit"));
        structureTree.setContextMenu(MenuFactory.newPopupMenu(treeActions));
        appControl.getMenuBar().add(MenuFactory.newMenu(treeActions, editMenuAction));

        contentEditorPane.setEditable(false);
        updatePageEditor();
    }

    public ReaderView getView(){
        return readerView;
    }

    public void actionPerformed(ActionEvent event){
        updatePageEditor();
    }

    private void updatePageEditor(){
        ProjectTreeNode selectedNode = (ProjectTreeNode) treeModel.getCurrentNode();
        PageModel selectedPage = selectedNode.getPageModel();
        contentEditorPane.setContentType(selectedPage.getContentType());
        contentEditorPane.setText(selectedPage.readContent());
    }
}

