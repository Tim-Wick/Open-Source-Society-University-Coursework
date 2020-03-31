import java.util.*;
import java.io.*;

// Program is for interpreting a very basic language, YazLang
public class YazInterpreter {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        intro();
        String userChoice;
        do {
            System.out.print("(C)onsole YazInteractions, (I)nterpret .yzy program, (Q)uit? ");
            userChoice = console.nextLine();
            if (userChoice.equalsIgnoreCase("C")) {
                consoleInteraction(console);
            } else if (userChoice.equalsIgnoreCase("I")) {
                fileInteraction(console);
            }
        } while (!userChoice.equalsIgnoreCase("q"));

    }

    public static void intro() {
        System.out.println("Welcome to the YazInterpreter!\nYou may interpret a YazLang program and output\nthe " +
                "results to a .txt file or enter console YazInteractions\nmode to run single commands of YazLang.");
    }

    public static String convert(int value, String unit) {
        if (unit.equalsIgnoreCase("c")) {
            value *= 1.8;
            value += 32;
            return (value + "F");
        } else {
            value -= 32;
            value /= 1.8;
            return (value + "C");
        }
    }

    public static String range(int first, int last, int jump) {
        String returnValues = "";
        while (first < last) {
            returnValues = returnValues.concat(String.valueOf(first)).concat(" ");
            first += jump;
        }
        return returnValues;
    }

    public static String repeat(String line) {
        String returnValues = "";
        Scanner repeat = new Scanner(line);
        while (repeat.hasNext()) {
            String repeatString = repeat.next();
            repeatString = repeatString.replace("\"", "").replace("_", " ");
            int times = repeat.nextInt();
            for (int i = 0; i < times; i++) {
                returnValues = returnValues.concat(repeatString);
            }
        }
        return returnValues;
    }

    public static String runCommands(String input) {
        Scanner commandLine = new Scanner(input);
        String command = commandLine.next();
        if (command.startsWith("C")) {
            return convert(commandLine.nextInt(), commandLine.next());
        } else if (command.startsWith("RA")) {
            return range(commandLine.nextInt(), commandLine.nextInt(), commandLine.nextInt());
        } else if (command.startsWith("RE")) {
            return repeat(commandLine.nextLine());
        } else {
            return command;
        }

    }

    public static void consoleInteraction(Scanner console) {
        System.out.println("YazInteractions session. Type END to exit.");
        String commandReturn;
        do {
            System.out.print("> ");
            commandReturn = runCommands(console.nextLine());
            if (!commandReturn.equalsIgnoreCase("end")) {
                System.out.println(commandReturn);
            }
        } while (!commandReturn.equalsIgnoreCase("end"));
    }

    public static void fileInteraction(Scanner console) throws FileNotFoundException {
        Scanner file = new Scanner(getFile(console));
        System.out.print("Output file name: ");
        String outputFileName = console.nextLine();
        PrintStream outputFile = new PrintStream(new File(outputFileName));
        while(file.hasNextLine()) {
            String text = file.nextLine();
            outputFile.println(runCommands(text));
        }
        System.out.println("YazLang program interpreted and output to " + outputFileName + "!");
    }

    public static File getFile(Scanner console) {
        System.out.print("Input file name: ");
        File file = new File(console.nextLine());
        while (!file.canRead()) {
            System.out.print("File not found. Try again: ");
            file = new File(console.nextLine());
        }
        return file;
    }

}

