package Controller;

import View.CLI;
import View.Menu;
import View.MyPanel;

import java.io.IOException;


import Model.Location;
import Model.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int fps = 10;
    static boolean cli = true;
    public static boolean diameter = false;

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();

            JFrame frame = new JFrame();
            String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
            MyPanel panel = MyPanel.getInstance();
            boolean gameFinished = false;
            int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            int maxSize = Math.max(screenWidth, screenHeight) / 3;
            panel.setSize(maxSize, maxSize);
            panel.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
            frame.setSize(panel.getSize());
            frame.setLocation(panel.getLocation());
            frame.add(panel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
            ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
            ArrayList<Integer> piecesRandomOrder = new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
            ArrayList<String> images = new JSON().imageReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
            Collections.shuffle(piecesRandomOrder);
            for (int i = 0; i < piecesRandomOrder.size(); i++)
                if (piecesRandomOrder.get(i) + 1 == JSON.getJson().heightReader(file) * JSON.getJson().widthReader(file))
                    panel.setMissingPiece(i);
            if (!solvable(panel.getMissingPiece(), piecesRandomOrder) && !diameter && JSON.getJson().heightReader(file) == JSON.getJson().widthReader(file)) {
                JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
                gameFinished = true;
            }
            for (int i = 0; i < piecesRandomOrder.size(); i++) {
                if (panel.getMissingPiece() != i) {
                    puzzlePieces.add(new PuzzlePiece(images.get(piecesRandomOrder.get(i)), new Location(panel.getHeight() / JSON.getJson().widthReader(file) * (i % JSON.getJson().widthReader(file)), panel.getWidth() / JSON.getJson().heightReader(file) * (i / JSON.getJson().widthReader(file)))));
                } else {
                    puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(panel.getHeight() / JSON.getJson().widthReader(file) * (i % JSON.getJson().widthReader(file)), panel.getWidth() / JSON.getJson().heightReader(file) * (i / JSON.getJson().widthReader(file)))));
                }
            }
            panel.setPuzzlePieces(puzzlePieces);
            frame.addKeyListener(new MyKeyListener());
            frame.setVisible(true);
            if (cli) {
                CLI.cliBoard();
            }

            while (true) {
                try {
                    Thread.sleep(fps);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                panel.repaint();
                frame.repaint();
                if (gameFinished) {
                    break;
                }
                if (panel.getGameState().equals("finished")) {
                    JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                    gameFinished = true;
                }
            }

    }

    public static boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j) && piecesOrder.get(i) != missingPiece) {
                    inversionCount += 1;
                }
            }
        }
        if ((int) Math.sqrt(piecesOrder.size()) % 2 == 1 || (missingPiece / (int) Math.sqrt(piecesOrder.size())) % 2 == 1) {
            return inversionCount % 2 == 0;
        }

        return inversionCount % 2 == 1;
    }
}


