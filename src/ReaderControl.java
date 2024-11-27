package org.berandev.byterover;

import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReaderControl implements Controller, ActionListener {
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

    public JPanel getView(){
        return readerView;
    }

    public void initControl(){
        MenuFactory menuFactory = appControl.getMenuFactory();
        structureTree.setContextMenu(menuFactory.newPopupMenu(ActionModel.STRUCTURE_TREE_MENU));
    }

    public void loadControl(){
        treeModel = appControl.getProjectModel();
        treeModel.setCurrentNode(treeModel.getDefaultNode());
        treeModel.addCurrentNodeListener(this);
        structureTree.setModel(treeModel);
        structureTree.setSelectionPath(treeModel.getCurrentPath());
        contentEditorPane.setEditable(false);
        updatePageEditor();
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

