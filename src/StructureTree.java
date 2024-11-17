package org.berandev.byterover;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.event.TreeExpansionEvent;
import java.util.EventObject;

public class StructureTree extends JTree {
    public void disableRootCollapse(){
        addTreeWillExpandListener(new TreeWillExpandListener(){
            public void treeWillExpand(TreeExpansionEvent event){}

            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
                if (event.getPath().getLastPathComponent().equals(getModel().getRoot())){
                    throw new ExpandVetoException(event);
                }
            }
        });
    }

    public void disableEditOnClick(){
        setCellEditor(new DefaultTreeCellEditor(this, (DefaultTreeCellRenderer) getCellRenderer()){
            @Override
            public boolean isCellEditable(EventObject event){
                return event == null;
            }
        });
    }
}

