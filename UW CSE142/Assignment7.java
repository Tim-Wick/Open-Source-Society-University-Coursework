import java.util.*;
import java.io.*;

// Class to take txt file of personality test results and determine personality type
public class Personality {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        introProgram();
        System.out.print("input file name? ");
        Scanner inputFile = new Scanner(new File(console.nextLine()));
        System.out.print("output file name? ");
        PrintStream outputFile = new PrintStream(new File(console.nextLine()));

        while (inputFile.hasNext()) {
            String name = inputFile.nextLine();
            String answers = inputFile.nextLine();
            int[] percentages = parsePercentages( parseAnswers(answers, "a"), parseAnswers(answers, "b"));
            String personalityType = parsePersonality(percentages);
            outputFile.println(name + ": " + Arrays.toString(percentages) + " = " + personalityType);
        }
    }

    // Method to print the introduction to the program
    public static void introProgram() {
        System.out.println("This program processes a file of answers to the\n" +
                "Keirsey Temperament Sorter. It converts the\n" +
                "various A and B answers for each person into\n" +
                "a sequence of B-percentages and then into a\n" +
                "four-letter personality type.");
    }

    // Method to take string of answers and either a or b and return array of answers split into four dimensions
    // This would have been easier to return in a two dimensional array with both but specifications stated to not use
    // multidimensional arrays
    public static int[] parseAnswers(String answers, String searchCharacter) {
        int[] tempArr = new int[7];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                if (answers.substring(j + i * 7, (j + i * 7) + 1).equalsIgnoreCase(searchCharacter)) {
                    tempArr[j]++;
                }
            }
        }
        return new int[]{tempArr[0], tempArr[1] + tempArr[2], tempArr[3] + tempArr[4], tempArr[5] + tempArr[6]};
    }

    // Method to take arrays of A and B answers for each dimension and return the percentage of B answers for each
    public static int[] parsePercentages(int[] numA, int[] numB) {
        int[] percentArray = new int[4];
        for (int i = 0; i < 4; i++) {
            percentArray[i] = (int)Math.round((double)numB[i] / (numA[i] + numB[i]) * 100);
        }
        return percentArray;
    }

    // Method to take an array of dimension percentages and return the personality type
    public static String parsePersonality(int[] percentageArray) {
        StringBuilder returnString = new StringBuilder();
        String[] typeArray = {"EI", "SN", "TF", "JP"};
        for (int i = 0; i < 4; i++) {
            if (percentageArray[i] < 50) {
                returnString.append(typeArray[i].charAt(0));
            } else if (percentageArray[i] > 50) {
                returnString.append(typeArray[i].charAt(1));
            } else {
                returnString.append("X");
            }
        }
        return returnString.toString();
    }
}
