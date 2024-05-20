package Controller;

import Model.Variables;
import View.Menu;
import View.MyPanel;

import java.io.IOException;

public class Main {
    static {
        try {
            Variables.variable.setPiecesRandomOrder(new JSON().orderReader("C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        MyPanel.panelMaker();
        new Menu();
        Logic.timer();
    }
}
