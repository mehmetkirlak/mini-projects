import java.io.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
//        map.put("mehmet","kirlak");
//        map.put("fazullah","demir");
//        map.put("garip","asalak");
//        map.put("kerem","gobi");

        start();
    }

    public static void start() throws IOException {


        boolean flag = false;
        while(!flag){
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = br.readLine();
            switch (action){
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case  "exit":
                    flag = exit();
                    break;
                case "import":
                    importFile();
                    break;
                case "export":
                    exportFile();
                    break;
                case "ask":
                    ask();
                    break;
            }
        }

    }

    public static void add() throws IOException {
        System.out.println("The card:");
        String term = br.readLine();
        if (!map.containsKey(term)) {
            map.put(term, null);
        } else {
            System.out.printf("The card \"%s\" already exists.\n\n", term);
            return;
        }
        System.out.printf("The definition of the card:\n");
        String definition = br.readLine();
        if (!map.containsValue(definition)) {
            map.put(term, definition);
            System.out.printf("The pair (\"%s\":\"%s\") has been added.\n\n", term, definition);
        } else {
            System.out.printf("The definition \"%s\" already exists.\n\n", definition);
        }
    }

    public static void remove() throws IOException {
        System.out.println("Which card?");
        String cardName = br.readLine();
        if (map.containsKey(cardName)){
            map.remove(cardName);
            System.out.println("The card has been removed.\n");
        } else {
            System.out.println("Can't remove \""+cardName+"\": there is no such card.\n");
        }
    }

    public static void ask() throws IOException {
        System.out.println("How many times to ask?");
        int input = Integer.parseInt(br.readLine());
        int counter = 0;
        Map<String, String> tempMap = map;

        for (var entry : tempMap.entrySet()) {
            if (counter < input) {
                System.out.print("Print the definition of \"" + entry.getKey() + "\":");
                String answer = br.readLine();
                if (answer.equals(entry.getValue())) {
                    System.out.printf("Correct!\n");
                } else {
                    if (map.containsValue(answer)) {
                        String correctAnswer = "";
                        for (var tempEntry: tempMap.entrySet()) {
                            if (tempEntry.getValue().equals(answer)) {
                                correctAnswer = tempEntry.getKey();
                                break;
                            }
                        }
                        System.out.printf(
                                "Wrong. The right answer is \"%s\", " +
                                        "but your definition is correct for \"%s\".\n\n", entry.getValue(), correctAnswer
                        );
                    } else {
                        System.out.printf("Wrong. The right answer is \"%s\".\n\n", entry.getValue());
                    }
                }
            } else {
                break;
            }
        }
    }

    public static void exportFile() throws IOException {
        System.out.println("File name:");
        Map<String, String> tempMap = map;
        String input = br.readLine();
        File file = new File("./" + input);
        try(FileWriter writer = new FileWriter(file, false)) {
            for (var entry : tempMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
            System.out.printf("%d cards have been saved.\n\n", tempMap.size());
        } catch (IOException e) {
            System.out.printf("An exception occurred %s\n\n", e.getMessage());
        }
    }
    public static void importFile() throws IOException {
        System.out.println("File name:");
        String input = br.readLine();
        int lineCounter = 0;
        File file = new File("./" + input);
        try {
            Scanner myReader = new Scanner(file);
            while(myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(":");
                if (map.containsKey(data[0])) {
                    map.replace(data[0], data[1]);
                }
                map.put(data[0], data[1]);
                lineCounter++;
            }
            System.out.println(lineCounter + " cards have been loaded.\n");
        } catch(FileNotFoundException e) {
            System.out.println("File not found\n");
        }
    }

    public static boolean exit(){
        System.out.println("Bye bye!");
        return true;
    }

    public static  <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}