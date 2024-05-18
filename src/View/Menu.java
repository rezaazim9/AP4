package View;

import Controller.JSON;
import Controller.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;


public class Menu implements ActionListener {
     public JFrame frame = new JFrame();
    JButton GUI = new JButton("GUI");
    JButton CLI = new JButton("CLI");
    JButton DIA = new JButton("Diameter");
    JButton nonDIA = new JButton("Non-Diameter");

    public Menu() {
        frame.setSize(400, 400);
        frame.setLocation(750, 150);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(GUI);
        frame.add(CLI);
        frame.add(DIA);
        frame.add(nonDIA);
        GUI.addActionListener(this);
        CLI.addActionListener(this);
        DIA.addActionListener(this);
        nonDIA.addActionListener(this);
        GUI.setBounds(150, 50, 100, 50);
        CLI.setBounds(150, 150, 100, 50);
        DIA.setBounds(100, 200, 100, 50);
        nonDIA.setBounds(200, 200, 100, 50);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GUI) {
            frame.dispose();
            Main.frame.setVisible(true);
            try {
                if (!Main.solvable(Main.panel.getMissingPiece(), Main.piecesRandomOrder) && !Main.diameter && JSON.getJson().heightReader(Main.file) == JSON.getJson().widthReader(Main.file)) {
                    JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
                    Main.gameFinished = true;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == CLI) {
            frame.dispose();
            new CLI();
        }
        if (e.getSource() == DIA) {
            Main.diameter = true;
        }
        if (e.getSource() == nonDIA) {
            Main.diameter = false;
        }
    }
}
