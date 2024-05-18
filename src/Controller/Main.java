package Controller;

import Model.Variables;
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
    public static boolean cli = false;
    public static boolean diameter = false;
    public static JFrame frame = new JFrame();
    public static boolean gameFinished = false;
    public static MyPanel panel = MyPanel.getInstance();
    public static ArrayList<Integer> piecesRandomOrder;

    static {
        try {
            piecesRandomOrder = new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
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
        ArrayList<String> images = new JSON().imageReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
        Collections.shuffle(piecesRandomOrder);
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (piecesRandomOrder.get(i) + 1 == JSON.getJson().heightReader(Variables.getFile()) * JSON.getJson().widthReader(Variables.getFile())) {
                panel.setMissingPiece(i);
            }
        }
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (panel.getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(images.get(piecesRandomOrder.get(i)), new Location(panel.getHeight() / JSON.getJson().widthReader(Variables.getFile()) * (i % JSON.getJson().widthReader(Variables.getFile())), panel.getWidth() / JSON.getJson().heightReader(Variables.getFile())* (i / JSON.getJson().widthReader(Variables.getFile())))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(panel.getHeight() / JSON.getJson().widthReader(Variables.getFile()) * (i % JSON.getJson().widthReader(Variables.getFile())), panel.getWidth() / JSON.getJson().heightReader(Variables.getFile()) * (i / JSON.getJson().widthReader(Variables.getFile())))));
            }
        }
        panel.setPuzzlePieces(puzzlePieces);
        frame.addKeyListener(new MyKeyListener());
        new Menu();
        while (true) {
            if (cli) {

                CLI.cliBoard();
                CLIScanner.setNewBoard(piecesRandomOrder);
            }
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
            if (panel.getGameState().equals("finished") || CLI.gameFinished(piecesRandomOrder)) {
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
