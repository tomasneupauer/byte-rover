package org.berandev.byterover;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class AppView {
    AppView (){
        FlatLightLaf.setup();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Byte Rover");
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 2, 15, 15));
        frame.add(panel);

        JButton button = new JButton("button");
        panel.add(button);

        JButton button1 = new JButton("button");
        panel.add(button1);

        JButton button2 = new JButton("button");
        panel.add(button2);

        JButton button3 = new JButton("button");
        panel.add(button3);

        JButton button4 = new JButton("button");
        panel.add(button4);

        JLabel label = new JLabel("label");
        panel.add(label);

        JSlider slider = new JSlider();
        panel.add(slider);

        JRadioButton radio = new JRadioButton("button");
        panel.add(radio);

        String vals[] = {"A", "B", "CDA", "ASD"};
        JComboBox cbox = new JComboBox(vals);
        panel.add(cbox);

        frame.pack();
    }

    public static void main(String[] args){
        new AppView();
    }
}

