package View;

import Controller.JSON;
import Controller.Main;
import Model.Variables;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            Variables.getFrame().setVisible(true);
            try {
                if (!Main.solvable(Variables.getPanel().getMissingPiece(), Variables.getPiecesRandomOrder()) && !Variables.isDiameter() && JSON.getJson().heightReader(Variables.getFile()) == JSON.getJson().widthReader(Variables.getFile())) {
                    JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
                   Variables.setGameFinished(true);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == CLI) {
            frame.dispose();

            Variables.setDiameter(true);
            try {
                if (!Main.solvable(Variables.getPanel().getMissingPiece(), Variables.getPiecesRandomOrder()) && !Variables.isDiameter()&& JSON.getJson().heightReader(Variables.getFile()) == JSON.getJson().widthReader(Variables.getFile())) {
                    JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == DIA) {
           Variables.setDiameter(true);
        }
        if (e.getSource() == nonDIA) {
            Variables.setDiameter(false);
        }
    }
}
