package org.berandev.byterover;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import java.awt.GridBagLayout;

public class PageView extends JPanel{
    private JPanel controlPanel;
    private JPanel circuitPanel;
    private JScrollPane circuitScrollPane;
    private JEditorPane contentEditorPane;
    private JScrollPane contentScrollPane;
    private JTree structureTree;

    public PageView(){
        setLayout(new GridBagLayout());
    }

    public JPanel newControlPanel(){
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        add(controlPanel, new Constraints(0, 0));
        return controlPanel;
    }

    public JTree newStructureTree(){
        structureTree = new JTree();
        controlPanel.add(structureTree, new Constraints(0, 0));
        return structureTree;
    }

    public JPanel newCircuitPanel(){
        circuitPanel = new JPanel();
        circuitPanel.setLayout(null);
        circuitScrollPane = new JScrollPane(circuitPanel);
        add(circuitScrollPane, new Constraints(1, 0));
        return circuitPanel;
    }

    public JEditorPane newContentEditorPane(){
       contentEditorPane = new JEditorPane();
       contentScrollPane = new JScrollPane(contentEditorPane);
       add(contentScrollPane, new Constraints(2, 0));
       return contentEditorPane;
    }
}

