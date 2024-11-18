package org.berandev.byterover;

import javax.swing.JTree;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.event.TreeExpansionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.util.EventObject;

public class StructureTree extends JTree {
    public StructureTree(){
        setEditable(true);
        setRowHeight(22);
        disableRootCollapse();
        disableEditOnTripleClick();
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    public void setContextMenu(JPopupMenu menu){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent event){
                if (event.getButton() != MouseEvent.BUTTON3){
                    return;
                }
                TreePath eventPath = getPathForMouseEvent(event);
                if (eventPath == null){
                    return;
                }
                setSelectionPath(eventPath);
                menu.show(StructureTree.this, event.getX(), event.getY());
            }
        });
    }

    public TreePath getPathForMouseEvent(MouseEvent event){
        TreePath eventPath = getClosestPathForLocation(event.getX(), event.getY());
        Rectangle nodeBounds = getPathBounds(eventPath);
        nodeBounds.setLocation(1, (int) nodeBounds.getY());
        nodeBounds.setSize(getWidth() - 2, (int) nodeBounds.getHeight());
        if (!nodeBounds.contains(event.getPoint())){
            return null;
        }
        return eventPath;
    }

    public String getSelectedNodeType(){
        Object selectedNode = getSelectionPath().getLastPathComponent();
        if (selectedNode instanceof ProjectTreeNode){
            ProjectTreeNode projectTreeNode = (ProjectTreeNode) selectedNode;
            if (projectTreeNode.getParent() == null){
                return ActionFactory.PROJECT_NODE;
            }
            else if (projectTreeNode.isPage()){
                return ActionFactory.PAGE_NODE;
            }
            else {
                return ActionFactory.GROUP_NODE;
            }
        }
        return null;
    }

    private void disableRootCollapse(){
        addTreeWillExpandListener(new TreeWillExpandListener(){
            public void treeWillExpand(TreeExpansionEvent event){}

            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                if (event.getPath().getLastPathComponent().equals(getModel().getRoot())){
                    throw new ExpandVetoException(event);
                }
            }
        });
    }

    private void disableEditOnTripleClick(){
        setCellEditor(new DefaultTreeCellEditor(this, (DefaultTreeCellRenderer) getCellRenderer()){
            @Override
            public boolean isCellEditable(EventObject event){
                return event == null;
            }
        });
    }
}

