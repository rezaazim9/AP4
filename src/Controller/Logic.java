package Controller;

import Model.Variables;
import View.CLI;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Logic {
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

    public static boolean gameFinished(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(1 + i)) {
                return false;
            }
        }
        return true;
    }

    public static void timer() throws IOException {
        while (true) {
            if (Variables.variable.isCli()) {
                CLI.cliBoard();
                CLIScanner.setNewBoard(Variables.variable.getPiecesRandomOrder());
            }
            try {
                Thread.sleep(Variables.variable.getFps());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Variables.variable.getPanel().repaint();
            Variables.variable.getFrame().repaint();
            if (Variables.variable.isGameFinished()) {
                break;
            }
            if (Variables.variable.getPanel().getGameState().equals("finished") || Logic.gameFinished(Variables.variable.getPiecesRandomOrder())) {
                JOptionPane.showMessageDialog(Variables.variable.getFrame(), "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                Variables.variable.setGameFinished(true);
            }
        }
    }
}
