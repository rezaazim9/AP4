package View;

import Controller.JSON;
import Model.PuzzlePiece;

import java.io.IOException;

public class CLI {
    public static void cliBoard() throws IOException {
        String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
        int count = 0;
        for (PuzzlePiece piece : MyPanel.getInstance().getPuzzlePieces()) {
            System.out.print(piece.getPieceNumber() + 1 + " ");
            count++;
            if (count == JSON.getJson().widthReader(file)) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }
}
