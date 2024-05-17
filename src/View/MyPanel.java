package View;

import Controller.JSON;
import Model.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    private static MyPanel panelInstance;
    private ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();

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

    private int missingPiece = 0;
    private String gameState = "#";

    String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";

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

    public boolean gameFinished() throws IOException {
        for (int i = 0; i < JSON.getJson().heightReader(file) * JSON.getJson().widthReader(file); i++) {
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
        String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            try {
                g.drawImage(piece.getImg(), piece.getLocation().getX(), piece.getLocation().getY(), (int) this.getSize().getWidth() / size.widthReader(file), (int) this.getSize().getHeight() / size.heightReader(file), null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
