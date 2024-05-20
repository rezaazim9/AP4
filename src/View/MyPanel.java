package View;

import Controller.JSON;
import Controller.MyKeyListener;
import Model.Location;
import Model.PuzzlePiece;
import Model.Variables;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MyPanel extends JPanel {
    private ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    private int missingPiece = 0;
    private String gameState = "#";
    private static MyPanel panelInstance;

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public static MyPanel getPanelInstance() {
        return panelInstance;
    }

    public static void setPanelInstance(MyPanel panelInstance) {
        MyPanel.panelInstance = panelInstance;
    }

    public ArrayList<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }

    public int getMissingPiece() {
        return missingPiece;
    }


    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
        }
        return panelInstance;
    }

    public void swapPieces(int i, int j) throws IOException {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).getImg());
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).getPieceNumber());
        puzzlePieces.get(j).setImage(copy.getImg());
        puzzlePieces.get(j).setPieceNumber(copy.getPieceNumber());
        if (gameFinished()) {
            gameState = "finished";
        }
    }

    public static void panelMaker() throws IOException {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        Variables.variable.getPanel().setSize(maxSize, maxSize);
        Variables.variable.getPanel().setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        Variables.variable.getFrame().setSize(Variables.variable.getPanel().getSize());
        Variables.variable.getFrame().setLocation(Variables.variable.getPanel().getLocation());
        Variables.variable.getFrame().add(Variables.variable.getPanel());
        Variables.variable.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        ArrayList<String> images = new JSON().imageReader(Variables.variable.getFile());
        Collections.shuffle(Variables.variable.variable.getPiecesRandomOrder());
        for (int i = 0; i < Variables.variable.getPiecesRandomOrder().size(); i++) {
            if (Variables.variable.getPiecesRandomOrder().get(i) + 1 == JSON.getJson().heightReader(Variables.variable.getFile()) * JSON.getJson().widthReader(Variables.variable.getFile())) {
                Variables.variable.getPanel().setMissingPiece(i);
            }
        }
        for (int i = 0; i < Variables.variable.getPiecesRandomOrder().size(); i++) {
            if (Variables.variable.getPanel().getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(images.get(Variables.variable.getPiecesRandomOrder().get(i)), new Location(Variables.variable.getPanel().getHeight() / JSON.getJson().widthReader(Variables.variable.getFile()) * (i % JSON.getJson().widthReader(Variables.variable.getFile())), Variables.variable.getPanel().getWidth() / JSON.getJson().heightReader(Variables.variable.getFile()) * (i / JSON.getJson().widthReader(Variables.variable.getFile())))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(Variables.variable.getPanel().getHeight() / JSON.getJson().widthReader(Variables.variable.getFile()) * (i % JSON.getJson().widthReader(Variables.variable.getFile())), Variables.variable.getPanel().getWidth() / JSON.getJson().heightReader(Variables.variable.getFile()) * (i / JSON.getJson().widthReader(Variables.variable.getFile())))));
            }
        }
        Variables.variable.getPanel().setPuzzlePieces(puzzlePieces);
        Variables.variable.getFrame().addKeyListener(new MyKeyListener());

    }

    public boolean gameFinished() throws IOException {
        for (int i = 0; i < JSON.getJson().heightReader(Variables.variable.getFile()) * JSON.getJson().widthReader(Variables.variable.getFile()); i++) {
            int pieceIdentifier = puzzlePieces.get(i).getPieceNumber();
            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        JSON size = new JSON();
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            try {
                g.drawImage(piece.getImg(), piece.getLocation().getX(), piece.getLocation().getY(), (int) this.getSize().getWidth() / size.widthReader(Variables.variable.getFile()), (int) this.getSize().getHeight() / size.heightReader(Variables.variable.getFile()), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
