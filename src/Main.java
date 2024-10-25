import java.io.*;
import javax.swing.*;

public class Main {
    JFrame frame;
    JButton button;
    Main (){
        frame = new JFrame();
        button = new JButton("button");
        frame.add(button);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }
}

