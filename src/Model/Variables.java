package Model;

import View.MyPanel;

import javax.swing.*;
import java.util.ArrayList;

public class Variables {
    public  static Variables variable=new Variables();
    private  String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";
    private  int fps = 10;
    private  boolean cli = false;
    private  boolean diameter = false;
    private  JFrame frame = new JFrame();
    private  boolean gameFinished = false;
    private  MyPanel panel = MyPanel.getInstance();
    private ArrayList<Integer> piecesRandomOrder;

    public  int getFps() {
        return fps;
    }

    public  void setFps(int fps) {
        variable.fps = fps;
    }

    public boolean isCli() {
        return cli;
    }

    public  void setCli(boolean cli) {
        variable.cli = cli;
    }

    public  boolean isDiameter() {
        return diameter;
    }

    public void setDiameter(boolean diameter) {
        variable.diameter = diameter;
    }

    public   JFrame getFrame() {
        return frame;
    }

    public   void setFrame(JFrame frame) {
        variable.frame = frame;
    }

    public   boolean isGameFinished() {
        return gameFinished;
    }

    public   void setGameFinished(boolean gameFinished) {
        variable.gameFinished = gameFinished;
    }

    public   MyPanel getPanel() {
        return panel;
    }

    public   void setPanel(MyPanel panel) {
        variable.panel = panel;
    }

    public   ArrayList<Integer> getPiecesRandomOrder() {
        return piecesRandomOrder;
    }

    public   void setPiecesRandomOrder(ArrayList<Integer> piecesRandomOrder) {
        variable.piecesRandomOrder = piecesRandomOrder;
    }

    public   String getFile() {
        return file;
    }

    public   void setFile(String file) {
        variable.file = file;
    }
}
