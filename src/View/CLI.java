package View;

import Controller.JSON;
import Model.Variables;

import java.io.IOException;

public class CLI {
    public static void cliBoard() throws IOException {
        int count = 0;
        for (Integer i : Variables.variable.getPiecesRandomOrder()) {
            System.out.print(i + 1 + " ");
            count++;
            if (count == JSON.getJson().widthReader(Variables.variable.getFile())) {
                System.out.println();
                count = 0;
            }
        }
        System.out.println();
    }
}
