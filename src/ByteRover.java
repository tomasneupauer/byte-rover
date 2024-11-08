package org.berandev.byterover;

import java.awt.EventQueue;

public class ByteRover {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            new AppView();
        });
    }
}

