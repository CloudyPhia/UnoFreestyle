package ui;

import ui.visuals.UnoFrame;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
//        try {
//            new UnoApplication();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found.");
//        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UnoFrame();
            }
        });
    }
}
