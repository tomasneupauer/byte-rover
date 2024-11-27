package org.berandev.byterover;

import java.awt.Component;

public interface Controller {
    Component getView();
    void initControl();
    void loadControl();
}

