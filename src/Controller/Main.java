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



    static {
        try {
            Variables.setPiecesRandomOrder(new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        Variables.getPanel().setSize(maxSize, maxSize);
        Variables.getPanel().setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        Variables.getFrame().setSize(Variables.getPanel().getSize());
        Variables.getFrame().setLocation(Variables.getPanel().getLocation());
        Variables.getFrame().add(Variables.getPanel());
        Variables.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        ArrayList<String> images = new JSON().imageReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json");
        Collections.shuffle(Variables.getPiecesRandomOrder());
        for (int i = 0; i < Variables.getPiecesRandomOrder().size(); i++) {
            if (Variables.getPiecesRandomOrder().get(i) + 1 == JSON.getJson().heightReader(Variables.getFile()) * JSON.getJson().widthReader(Variables.getFile())) {
                Variables.getPanel().setMissingPiece(i);
            }
        }
        for (int i = 0; i < Variables.getPiecesRandomOrder().size(); i++) {
            if ( Variables.getPanel().getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(images.get(Variables.getPiecesRandomOrder().get(i)), new Location( Variables.getPanel().getHeight() / JSON.getJson().widthReader(Variables.getFile()) * (i % JSON.getJson().widthReader(Variables.getFile())),  Variables.getPanel().getWidth() / JSON.getJson().heightReader(Variables.getFile())* (i / JSON.getJson().widthReader(Variables.getFile())))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location( Variables.getPanel().getHeight() / JSON.getJson().widthReader(Variables.getFile()) * (i % JSON.getJson().widthReader(Variables.getFile())),  Variables.getPanel().getWidth() / JSON.getJson().heightReader(Variables.getFile()) * (i / JSON.getJson().widthReader(Variables.getFile())))));
            }
        }
        Variables.getPanel().setPuzzlePieces(puzzlePieces);
        Variables.getFrame().addKeyListener(new MyKeyListener());
        new Menu();
        while (true) {
            if (Variables.isCli()) {

                CLI.cliBoard();
                CLIScanner.setNewBoard(Variables.getPiecesRandomOrder());
            }
            try {
                Thread.sleep(Variables.getFps());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Variables.getPanel().repaint();
           Variables.getFrame().repaint();
            if (Variables.isGameFinished()) {
                break;
            }
            if ( Variables.getPanel().getGameState().equals("finished") || CLI.gameFinished(Variables.getPiecesRandomOrder())) {
                JOptionPane.showMessageDialog(Variables.getFrame(), "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                Variables.setGameFinished(true);
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
