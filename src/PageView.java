package org.berandev.byterover;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class PageView extends JPanel{
    private ProjectModel projectModel;

    private JPanel controlPanel;
    private JTree projectTree;
    private JPanel contentPanel;

    public PageView (ProjectModel model){
        projectModel = model;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints;
        buildProjectTree();

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.green);
        
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(controlPanel, constraints);

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.red);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        add(contentPanel, constraints);

        controlPanel.add(projectTree);
        contentPanel.add(new JTree(buildTreeLayer(projectModel.getProjectStructureRoot())));
    }

    private void buildProjectTree(){
        projectTree = new JTree(buildTreeLayer(projectModel.getProjectStructureRoot()));
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

