package Model;

import View.MyPanel;

import javax.swing.*;
import java.util.ArrayList;

public class Variables {
    private static String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
    private static int fps = 10;
    private static boolean cli = false;
    private static boolean diameter = false;
    private static JFrame frame = new JFrame();
    private static boolean gameFinished = false;
    private static MyPanel panel = MyPanel.getInstance();
    private static ArrayList<Integer> piecesRandomOrder;

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        Variables.fps = fps;
    }

    public static boolean isCli() {
        return cli;
    }

    public static void setCli(boolean cli) {
        Variables.cli = cli;
    }

    public static boolean isDiameter() {
        return diameter;
    }

    public static void setDiameter(boolean diameter) {
        Variables.diameter = diameter;
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        Variables.frame = frame;
    }

    public static boolean isGameFinished() {
        return gameFinished;
    }

    public static void setGameFinished(boolean gameFinished) {
        Variables.gameFinished = gameFinished;
    }

    public static MyPanel getPanel() {
        return panel;
    }

    public static void setPanel(MyPanel panel) {
        Variables.panel = panel;
    }

    public static ArrayList<Integer> getPiecesRandomOrder() {
        return piecesRandomOrder;
    }

    public static void setPiecesRandomOrder(ArrayList<Integer> piecesRandomOrder) {
        Variables.piecesRandomOrder = piecesRandomOrder;
    }

    public static String getFile() {
        return file;
    }

    public static void setFile(String file) {
        Variables.file = file;
    }
}
