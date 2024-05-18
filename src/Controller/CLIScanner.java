package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIScanner {
    static Scanner scanner = new Scanner(System.in);
    static String file = "C:\\Users\\ostad\\IdeaProjects\\AP4\\src\\assets\\config.json";

    public static void setNewBoard(ArrayList<Integer> list) throws IOException {
        String choice = scanner.next();
        int save;
        int missingPoint = 0;
        for (Integer i : list) {
            if (JSON.getJson().heightReader(file) * JSON.getJson().widthReader(file) - 1 == i) {
                missingPoint = list.indexOf(i);
            }
        }
        if (choice.equals("u") && missingPoint >= JSON.getJson().widthReader(file)) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader(file)));
            list.set(missingPoint - JSON.getJson().widthReader(file), save);
        }
        if (choice.equals("d") && missingPoint < JSON.getJson().widthReader(file) * (JSON.json.heightReader(file) - 1)) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader(file)));
            list.set(missingPoint + JSON.getJson().widthReader(file), save);
        }
        if (choice.equals("l") && missingPoint % JSON.getJson().widthReader(file) != 0) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint - 1));
            list.set(missingPoint - 1, save);
        }
        if (choice.equals("r") && missingPoint % JSON.getJson().widthReader(file) != JSON.getJson().widthReader(file) - 1) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint + 1));
            list.set(missingPoint + 1, save);
        }
        if (Main.diameter) {
            if (choice.equals("q") && missingPoint >= JSON.getJson().widthReader(file) && missingPoint % JSON.getJson().widthReader(file) != 0) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader(file) - 1));
                list.set(missingPoint - JSON.getJson().widthReader(file) - 1, save);
            }
            if (choice.equals("w") && missingPoint >= JSON.getJson().widthReader(file) && missingPoint % JSON.getJson().widthReader(file) != JSON.getJson().widthReader(file) - 1) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader(file) + 1));
                list.set(missingPoint - JSON.getJson().widthReader(file) + 1, save);
            }
            if (choice.equals("s") && missingPoint < JSON.getJson().widthReader(file) * (JSON.json.heightReader(file) - 1) && missingPoint % JSON.getJson().widthReader(file) != JSON.getJson().widthReader(file) - 1) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader(file) + 1));
                list.set(missingPoint + JSON.getJson().widthReader(file) + 1, save);
            }
            if (choice.equals("a") & missingPoint < JSON.getJson().widthReader(file) * (JSON.json.heightReader(file) - 1) && missingPoint % JSON.getJson().widthReader(file) != 0) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader(file) - 1));
                list.set(missingPoint + JSON.getJson().widthReader(file) - 1, save);
            }
        }
    }
}