package org.berandev.byterover;

import javax.swing.JPanel;

public interface Controller {
    JPanel getView();
    void initControl();
    void loadControl();
}

