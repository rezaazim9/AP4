package Controller;

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

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        JSON json = new JSON();
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
        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        ArrayList<Integer> piecesRandomOrder = new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
        ArrayList<String> images = new JSON().imageReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
        Collections.shuffle(piecesRandomOrder);
        for (int i = 0; i < piecesRandomOrder.size(); i++)
            if (piecesRandomOrder.get(i) + 1 == json.heightReader(file) * json.widthReader(file))
                panel.setMissingPiece(i);
        if (!solvable(panel.missingPiece, piecesRandomOrder) && json.heightReader(file) == 3 && json.widthReader(file) == 3 && !MyKeyListener.diameter) {
            JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            gameFinished = true;
        }
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (panel.missingPiece != i) {
                puzzlePieces.add(new PuzzlePiece(images.get(piecesRandomOrder.get(i)), new Location(panel.getHeight() / json.widthReader(file) * (i % json.widthReader(file)), panel.getWidth() / json.heightReader(file) * (i / json.widthReader(file)))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(panel.getHeight() / json.widthReader(file) * (i % json.widthReader(file)), panel.getWidth() / json.heightReader(file) * (i / json.widthReader(file)))));
            }
        }
        panel.setPuzzlePieces(puzzlePieces);
        frame.addKeyListener(new MyKeyListener());
        frame.setVisible(true);
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
            if (panel.gameState.equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                gameFinished = true;
            }
        }
    }

    public static boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }
        int parity = inversionCount % 2;
        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));
        parity ^= (distanceOfMissingPiece % 2);
        return parity == 0;
    }
}


