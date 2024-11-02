package org.berandev.byterover;

import java.awt.GridBagConstraints;

public class Constraints extends GridBagConstraints{
    public Constraints(int gridx, int gridy){
        super();
        this.gridx = gridx;
        this.gridy = gridy;
        weightx = 1.0;
        weighty = 1.0;
        fill = BOTH;
    }
}

