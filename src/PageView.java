package org.berandev.byterover;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.GridBagLayout;

public class PageView extends JPanel{
    private ProjectModel projectModel;

    private JPanel controlPanel;
    private JTree projectTree;
    
    private JScrollPane circuitScrollPane;
    private JPanel circuitPanel;

    private JScrollPane contentScrollPane;
    private JEditorPane contentEditorPane;

    public PageView (ProjectModel model){
        projectModel = model;
        setLayout(new GridBagLayout());

        // WEST
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        add(controlPanel, new Constraints(0, 0));

        buildProjectTree();
        controlPanel.add(projectTree, new Constraints(0, 0));

        // CENTER
        circuitPanel = new JPanel();
        circuitPanel.setLayout(null);

        circuitScrollPane = new JScrollPane(circuitPanel);
        add(circuitScrollPane, new Constraints(1, 0));

        // EAST
        contentEditorPane = new JEditorPane();
        contentEditorPane.setContentType(projectModel.getPageContentType());
        contentEditorPane.setText(projectModel.getPageContent());
        contentEditorPane.setEditable(false);

        contentScrollPane = new JScrollPane(contentEditorPane);
        add(contentScrollPane, new Constraints(2, 0));
    }

    private void buildProjectTree(){
        projectTree = new JTree(buildTreeLayer(projectModel.getProjectStructure()));
    }

    private DefaultMutableTreeNode buildTreeLayer(Object[] structure){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(structure[0]);
        for (int i=1; i<structure.length; i++){
            if (structure[i] instanceof String){
                root.add(new DefaultMutableTreeNode(structure[i]));
            }
            else {
                root.add(buildTreeLayer((Object[]) structure[i]));
            }
        }
        return root;
    }
}

