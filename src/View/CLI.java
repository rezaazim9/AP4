package View;

import Controller.JSON;
import Controller.Main;
import Model.PuzzlePiece;

import java.io.IOException;
import java.util.ArrayList;

public class CLI {
    public static void cliBoard() throws IOException {
        String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
        int count = 0;
        for (Integer i : Main.piecesRandomOrder) {
            System.out.print(i + 1 + " ");
            count++;
            if (count == JSON.getJson().widthReader(file)) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }

    public static boolean gameFinished(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(1 + i)) {
                return false;
            }
        }
        return true;
    }
}
