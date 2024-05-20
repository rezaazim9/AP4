package View;

import Controller.JSON;
import Controller.Main;
import Model.Variables;

import java.io.IOException;
import java.util.ArrayList;

public class CLI {
    public static void cliBoard() throws IOException {
        int count = 0;
        for (Integer i :  Variables.variable.getPiecesRandomOrder()) {
            System.out.print(i + 1 + " ");
            count++;
            if (count == JSON.getJson().widthReader( Variables.variable.getFile())) {
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
