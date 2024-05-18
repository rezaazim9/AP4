package Controller;

import Model.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIScanner {
    static Scanner scanner = new Scanner(System.in);

    public static void setNewBoard(ArrayList<Integer> list) throws IOException {
        String choice = scanner.next();
        int save;
        int missingPoint = 0;
        for (Integer i : list) {
            if (JSON.getJson().heightReader(Variables.getFile()) * JSON.getJson().widthReader(Variables.getFile()) - 1 == i) {
                missingPoint = list.indexOf(i);
            }
        }
        if (choice.equals("u") && missingPoint >= JSON.getJson().widthReader(Variables.getFile())) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader(Variables.getFile())));
            list.set(missingPoint - JSON.getJson().widthReader(Variables.getFile()), save);
        }
        if (choice.equals("d") && missingPoint < JSON.getJson().widthReader(Variables.getFile()) * (JSON.json.heightReader(Variables.getFile())- 1)) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader(Variables.getFile())));
            list.set(missingPoint + JSON.getJson().widthReader(Variables.getFile()), save);
        }
        if (choice.equals("l") && missingPoint % JSON.getJson().widthReader(Variables.getFile()) != 0) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint - 1));
            list.set(missingPoint - 1, save);
        }
        if (choice.equals("r") && missingPoint % JSON.getJson().widthReader(Variables.getFile()) != JSON.getJson().widthReader(Variables.getFile()) - 1) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint + 1));
            list.set(missingPoint + 1, save);
        }
        if (Main.diameter) {
            if (choice.equals("q") && missingPoint >= JSON.getJson().widthReader(Variables.getFile()) && missingPoint % JSON.getJson().widthReader(Variables.getFile())!= 0) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader(Variables.getFile())- 1));
                list.set(missingPoint - JSON.getJson().widthReader(Variables.getFile())- 1, save);
            }
            if (choice.equals("w") && missingPoint >= JSON.getJson().widthReader(Variables.getFile())&& missingPoint % JSON.getJson().widthReader(Variables.getFile()) != JSON.getJson().widthReader(Variables.getFile())- 1) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader(Variables.getFile()) + 1));
                list.set(missingPoint - JSON.getJson().widthReader(Variables.getFile()) + 1, save);
            }
            if (choice.equals("s") && missingPoint < JSON.getJson().widthReader(Variables.getFile()) * (JSON.json.heightReader(Variables.getFile()) - 1) && missingPoint % JSON.getJson().widthReader(Variables.getFile()) != JSON.getJson().widthReader(Variables.getFile()) - 1) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader(Variables.getFile()) + 1));
                list.set(missingPoint + JSON.getJson().widthReader(Variables.getFile()) + 1, save);
            }
            if (choice.equals("a") & missingPoint < JSON.getJson().widthReader(Variables.getFile()) * (JSON.json.heightReader(Variables.getFile()) - 1) && missingPoint % JSON.getJson().widthReader(Variables.getFile())!= 0) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader(Variables.getFile()) - 1));
                list.set(missingPoint + JSON.getJson().widthReader(Variables.getFile())- 1, save);
            }
        }
    }
}