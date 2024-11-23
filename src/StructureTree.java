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
        setRowHeight(25);
        changeCurrentOnDoubleClick();
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
                setSelectionPath(getPathForMouseEvent(event));
                menu.show(StructureTree.this, event.getX(), event.getY());
            }
        });
    }

    public TreePath getPathForMouseEvent(MouseEvent event){
        TreePath eventPath = getClosestPathForLocation(event.getX(), event.getY());
        Rectangle nodeBounds = getPathBounds(eventPath);
        nodeBounds.setLocation(1, (int) nodeBounds.getY());
        nodeBounds.setSize(getWidth() - 2, (int) nodeBounds.getHeight());
        return nodeBounds.contains(event.getPoint()) ? eventPath : null;
    }

    public TreePath getContextPath(){
        if (getSelectionCount() == 0){
            StructureTreeNode rootNode = (StructureTreeNode) getModel().getRoot();
            return new TreePath(rootNode.getPath());
        }
        return getSelectionPath();
    }

    public StructureTreeNode getContextNode(){
        return (StructureTreeNode) getContextPath().getLastPathComponent();
    }

    private void changeCurrentOnDoubleClick(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent event){
                if (event.getButton() != MouseEvent.BUTTON1){
                    return;
                }
                if (event.getClickCount() != 2){
                    return;
                }
                TreePath eventPath = getPathForMouseEvent(event);
                if (eventPath == null){
                    return;
                }
                Object eventObject = eventPath.getLastPathComponent();
                StructureTreeNode eventNode = (StructureTreeNode) eventObject;
                if (eventNode.isShowable()){
                    ((StructureTreeModel) getModel()).setCurrentNode(eventNode);
                }
            }
        });
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

