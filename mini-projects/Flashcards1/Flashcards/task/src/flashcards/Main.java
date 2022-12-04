package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FlashCards fc = new FlashCards();
        boolean saveOnExit = false;
        String saveOnExitFileName = "";
        if (args.length>0){ // import mehmet.txt export ahmet.txt import veli.txt export dayı.txt
            for (int i = 0 ; i < args.length ; i+=2) {
                if (args[i] == "-import") {
                    fc.importCardsAsMainArgs(args[i+1]);
                } else {
                    saveOnExitFileName = args[i+1];
                    saveOnExit = true;
                }
            }
        }
        fc.mainMenu();
        if (saveOnExit){
            fc.exportCardsAsMainArgs(saveOnExitFileName);
        }
    }
}

class FlashCards {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, String> flashCards = new HashMap<>();
    private final Map<String, Integer> cardStats = new HashMap<>();
    private final StringBuilder log = new StringBuilder();

    public void mainMenu() {
        boolean flag = true;
        while (flag) {
            printlnLog("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            switch (scNextLineLog()) {
                case "add": {
                    addCard();
                    break;
                }
                case "remove": {
                    removeCard();
                    break;
                }
                case "import": {
                    importCards();
                    break;
                }
                case "export": {
                    exportCards();
                    break;
                }
                case "ask": {
                    askCards();
                    break;
                }
                case "log": {
                    saveLog();
                    break;
                }
                case "hardest card": {
                    hardestStats();
                    break;
                }
                case "reset stats": {
                    resetStats();
                    break;
                }
                case "exit": {
                    printlnLog("Bye bye!");
                    flag = false;
                }
            }
        }
    }

    private void addCard() {
        printlnLog("The card:");
        String term = scNextLineLog();
        if (dupPresent(term, 0)) {
            printlnLog("The card \"" + term +"\" already exists.\n");
            return;
        }
        printlnLog("The definition of the card:");
        String def = scNextLineLog();
        if (dupPresent(def, 1)) {
            printlnLog("The definition \"" + def + "\" already exists.\n");
        } else {
            flashCards.put(term, def);
            cardStats.put(term, 0);
            printlnLog("The pair (\"" + term + "\":\"" + def + "\") has been added.\n");
        }
    }

    private void removeCard() {
        printlnLog("Which card?");
        String s = scNextLineLog();
        if (flashCards.containsKey(s)) {
            flashCards.remove(s);
            cardStats.remove(s);
            printlnLog("The card has been removed.\n");
        } else {
            printlnLog("Can't remove \"" + s + "\": there is no such card.\n");
        }
    }

    private void importCards() {
        printlnLog("File name:");
        File file = new File(scNextLineLog());
        int count = 0;
        String[] s;
        if (file.exists()) {
            try (Scanner fileS = new Scanner(file)) {
                while (fileS.hasNext()) {
                    s = fileS.nextLine().split("=");
                    flashCards.put(s[0], s[1]);
                    cardStats.put(s[0], Integer.parseInt(s[2]));
                    count++;
                }
                printlnLog(count + " cards have been loaded.\n");
            } catch (IOException ex) {
                printlnLog("Problem with a file. " + ex.getMessage() + ".\n");
            }
        } else {
            printlnLog("File not found.\n");
        }
    }

    public void importCardsAsMainArgs(String fileName) {
        File file = new File(fileName);
        int count = 0;
        String[] s;
        if (file.exists()) {
            try (Scanner fileS = new Scanner(file)) {
                while (fileS.hasNext()) {
                    s = fileS.nextLine().split("=");
                    flashCards.put(s[0], s[1]);
                    cardStats.put(s[0], Integer.parseInt(s[2]));
                    count++;
                }
                printlnLog(count + " cards have been loaded.\n");
            } catch (IOException ex) {
                printlnLog("Problem with a file. " + ex.getMessage() + ".\n");
            }
        } else {
            printlnLog("File not found.\n");
        }
    }

    private void exportCards() {
        printlnLog("File name:");
        File file = new File(scNextLineLog());
        try (FileWriter fw = new FileWriter(file)) {
            for (var entry : flashCards.entrySet()) {
                fw.write(entry.getKey() + "=" + entry.getValue() + "=" + cardStats.get(entry.getKey()) + "\n");
            }
            printlnLog(flashCards.size() + " cards have been saved.\n");
        } catch (IOException ex) {
            printlnLog("Problem with a file. " + ex.getMessage() + ".\n");
        }
    }

    public void exportCardsAsMainArgs(String fileName) {
        File file = new File(fileName);
        try (FileWriter fw = new FileWriter(file)) {
            for (var entry : flashCards.entrySet()) {
                fw.write(entry.getKey() + "=" + entry.getValue() + "=" + cardStats.get(entry.getKey()) + "\n");
            }
            printlnLog(flashCards.size() + " cards have been saved.\n");
        } catch (IOException ex) {
            printlnLog("Problem with a file. " + ex.getMessage() + ".\n");
        }
    }

    private void askCards() {
        printlnLog("How many times to ask?");
        int t = Integer.parseInt(scNextLineLog()) ;
        boolean correct = false;
        for (var entry : flashCards.entrySet()) {
            if (t < 1) { break; }
            printlnLog("Print the definition of \"" + entry.getKey() + "\":");
            String def = scNextLineLog();
            if (entry.getValue().equals(def)) {
                printlnLog("Correct!");
                correct = true;
            }
            if (!correct) {
                printLog("Wrong. The right answer is \"" + entry.getValue() + "\"");
                if (flashCards.containsValue(def)) {
                    for (String key : flashCards.keySet()) {
                        if (flashCards.get(key).equals(def)) {
                            printlnLog(", but your definition is correct for \"" + key +"\".\n");
                        }
                    }
                } else {
                    printlnLog(".\n");
                }
                cardStats.put(entry.getKey(), cardStats.get(entry.getKey()) + 1);
            }
            correct = false;
            t--;
        }
    }

    //type 0 - term, type 1 - definition
    private boolean dupPresent(String s, int type) {
        if (type == 0 && flashCards.containsKey(s)) {
            return true;
        }
        if (type == 1 && flashCards.containsValue(s)) {
            return true;
        }
        return false;
    }

    private void saveLog() {
        printlnLog("File name:");
        File file = new File(scNextLineLog());
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(log.toString());
            printlnLog("The log has been saved.\n");
        } catch (IOException ex) {
            printlnLog("Problem with a file. " + ex.getMessage() + ".\n");
        }
    }

    private void hardestStats() {
        List<String> maxKeys = new ArrayList<>();
        int max = 0;
        for (var entry : cardStats.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        if (max == 0) {
            printlnLog("There are no cards with errors.\n");
            return;
        }
        for (var entry : cardStats.entrySet()) {
            if (entry.getValue() == max) {
                maxKeys.add(entry.getKey());
            }
        }
        if (maxKeys.size() == 1) {
            printlnLog("The hardest card is \"" + maxKeys.get(0)  + "\". You have " + max + " errors answering it.\n");
        } else {
            printLog("The hardest cards are ");
            for (int i = 0; i < maxKeys.size(); i++) {
                if (i == maxKeys.size() - 1) {
                    printLog("\"" + maxKeys.get(i) + "\".\n");
                    break;
                }
                printLog("\"" + maxKeys.get(i) + "\", ");
            }
            printLog("You have" + max + "errors answering them.\n");
        }
    }

    private void resetStats() {
        for (String key : cardStats.keySet()) {
            cardStats.replace(key, 0);
        }
        printlnLog("Card statistics have been reset.\n");
    }

    private void printLog(String s) {
        log.append(s);
        System.out.print(s);
    }

    private void printlnLog(String s) {
        log.append(s).append("\n");
        System.out.println(s);
    }

    private String scNextLineLog() {
        String s = scanner.nextLine();
        log.append(s).append("\n");
        return s;
    }

}