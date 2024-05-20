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
            if (JSON.getJson().heightReader( Variables.variable.getFile()) * JSON.getJson().widthReader( Variables.variable.getFile()) - 1 == i) {
                missingPoint = list.indexOf(i);
            }
        }
        if (choice.equals("u") && missingPoint >= JSON.getJson().widthReader( Variables.variable.getFile())) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader( Variables.variable.getFile())));
            list.set(missingPoint - JSON.getJson().widthReader( Variables.variable.getFile()), save);
        }
        if (choice.equals("d") && missingPoint < JSON.getJson().widthReader( Variables.variable.getFile()) * (JSON.json.heightReader( Variables.variable.getFile())- 1)) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader( Variables.variable.getFile())));
            list.set(missingPoint + JSON.getJson().widthReader( Variables.variable.getFile()), save);
        }
        if (choice.equals("l") && missingPoint % JSON.getJson().widthReader( Variables.variable.getFile()) != 0) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint - 1));
            list.set(missingPoint - 1, save);
        }
        if (choice.equals("r") && missingPoint % JSON.getJson().widthReader( Variables.variable.getFile()) != JSON.getJson().widthReader( Variables.variable.getFile()) - 1) {
            save = list.get(missingPoint);
            list.set(missingPoint, list.get(missingPoint + 1));
            list.set(missingPoint + 1, save);
        }
        if ( Variables.variable.isDiameter()) {
            if (choice.equals("q") && missingPoint >= JSON.getJson().widthReader( Variables.variable.getFile()) && missingPoint % JSON.getJson().widthReader( Variables.variable.getFile())!= 0) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader( Variables.variable.getFile())- 1));
                list.set(missingPoint - JSON.getJson().widthReader( Variables.variable.getFile())- 1, save);
            }
            if (choice.equals("w") && missingPoint >= JSON.getJson().widthReader( Variables.variable.getFile())&& missingPoint % JSON.getJson().widthReader( Variables.variable.getFile()) != JSON.getJson().widthReader( Variables.variable.getFile())- 1) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint - JSON.getJson().widthReader( Variables.variable.getFile()) + 1));
                list.set(missingPoint - JSON.getJson().widthReader( Variables.variable.getFile()) + 1, save);
            }
            if (choice.equals("s") && missingPoint < JSON.getJson().widthReader( Variables.variable.getFile()) * (JSON.json.heightReader( Variables.variable.getFile()) - 1) && missingPoint % JSON.getJson().widthReader( Variables.variable.getFile()) != JSON.getJson().widthReader( Variables.variable.getFile()) - 1) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader( Variables.variable.getFile()) + 1));
                list.set(missingPoint + JSON.getJson().widthReader( Variables.variable.getFile()) + 1, save);
            }
            if (choice.equals("a") & missingPoint < JSON.getJson().widthReader( Variables.variable.getFile()) * (JSON.json.heightReader( Variables.variable.getFile()) - 1) && missingPoint % JSON.getJson().widthReader( Variables.variable.getFile())!= 0) {
                save = list.get(missingPoint);
                list.set(missingPoint, list.get(missingPoint + JSON.getJson().widthReader( Variables.variable.getFile()) - 1));
                list.set(missingPoint + JSON.getJson().widthReader( Variables.variable.getFile())- 1, save);
            }
        }
    }
}