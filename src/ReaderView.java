package org.berandev.byterover;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTree;

public class ReaderView extends JPanel{
    private JPanel controlPanel;
    //private JPanel circuitPanel;
    //private JScrollPane circuitScrollPane;
    private JEditorPane contentEditorPane;
    private JScrollPane contentScrollPane;
    private JTree structureTree;
    private JScrollPane structureScrollPane;

    public ReaderView(){
        controlPanel = new JPanel();
        //circuitPanel = new JPanel();
        //circuitScrollPane = new JScrollPane(circuitPanel);
        contentEditorPane = new JEditorPane();
        contentScrollPane = new JScrollPane(contentEditorPane);
        structureTree = new StructureTree();
        structureScrollPane = new JScrollPane(structureTree);
        initComponents();
    }

    private void initComponents(){
        add(controlPanel);
        //add(circuitScrollPane);
        add(contentScrollPane);
        controlPanel.add(structureScrollPane);
    }

    public JTree getStructureTree(){
        return structureTree;
    }

    public JEditorPane getContentEditorPane(){
        return contentEditorPane;
    }
}

