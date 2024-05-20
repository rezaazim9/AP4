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
             Variables.variable.setPiecesRandomOrder(new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
         Variables.variable.getPanel().setSize(maxSize, maxSize);
         Variables.variable.getPanel().setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
         Variables.variable.getFrame().setSize( Variables.variable.getPanel().getSize());
         Variables.variable.getFrame().setLocation( Variables.variable.getPanel().getLocation());
         Variables.variable.getFrame().add( Variables.variable.getPanel());
         Variables.variable.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        ArrayList<String> images = new JSON().imageReader( Variables.variable.getFile());
        Collections.shuffle(  Variables.variable.variable.getPiecesRandomOrder());
        for (int i = 0; i <  Variables.variable.getPiecesRandomOrder().size(); i++) {
            if ( Variables.variable.getPiecesRandomOrder().get(i) + 1 == JSON.getJson().heightReader( Variables.variable.getFile()) * JSON.getJson().widthReader( Variables.variable.getFile())) {
                 Variables.variable.getPanel().setMissingPiece(i);
            }
        }
        for (int i = 0; i <  Variables.variable.getPiecesRandomOrder().size(); i++) {
            if ( Variables.variable.getPanel().getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(images.get( Variables.variable.getPiecesRandomOrder().get(i)), new Location( Variables.variable.getPanel().getHeight() / JSON.getJson().widthReader( Variables.variable.getFile()) * (i % JSON.getJson().widthReader( Variables.variable.getFile())),  Variables.variable.getPanel().getWidth() / JSON.getJson().heightReader( Variables.variable.getFile()) * (i / JSON.getJson().widthReader( Variables.variable.getFile())))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location( Variables.variable.getPanel().getHeight() / JSON.getJson().widthReader( Variables.variable.getFile()) * (i % JSON.getJson().widthReader( Variables.variable.getFile())),  Variables.variable.getPanel().getWidth() / JSON.getJson().heightReader( Variables.variable.getFile()) * (i / JSON.getJson().widthReader( Variables.variable.getFile())))));
            }
        }
         Variables.variable.getPanel().setPuzzlePieces(puzzlePieces);
         Variables.variable.getFrame().addKeyListener(new MyKeyListener());
        new Menu();
        while (true) {
            if ( Variables.variable.isCli()) {

                CLI.cliBoard();
                CLIScanner.setNewBoard( Variables.variable.getPiecesRandomOrder());
            }
            try {
                Thread.sleep( Variables.variable.getFps());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             Variables.variable.getPanel().repaint();
             Variables.variable.getFrame().repaint();
            if ( Variables.variable.isGameFinished()) {
                break;
            }
            if ( Variables.variable.getPanel().getGameState().equals("finished") || CLI.gameFinished( Variables.variable.getPiecesRandomOrder())) {
                JOptionPane.showMessageDialog( Variables.variable.getFrame(), "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                 Variables.variable.setGameFinished(true);
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
