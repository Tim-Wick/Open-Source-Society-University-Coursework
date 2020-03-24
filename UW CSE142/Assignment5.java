import java.util.*;

public class GuessingGame {
    public static final int MAX = 100;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        printIntro();
        int totalGames = 0;
        int totalGuesses = 0;
        int bestGame = MAX + 1;
        boolean playNext = true;
        while (playNext) {
            int guesses = playGame(getNumber(), console);
            totalGames++;
            totalGuesses += guesses;
            if (guesses < bestGame) {
                bestGame = guesses;
            }
            playNext = playAgain(console);
            System.out.println();
        }
        printResults(totalGames, totalGuesses, bestGame);
    }

    public static void printIntro() {
        System.out.println("This is a fun game.");
        System.out.println("This game will challenge your brain.");
        System.out.println("Guess number between...");
    }

    public static int getNumber() {
        Random r = new Random();
        int num = r.nextInt(MAX) + 1;
        return num;
    }

    public static boolean playAgain(Scanner console) {
        System.out.print("Do you want to play again? ");
        char yesNo = console.next().charAt(0);
        String noYes = Character.toString(yesNo);
        if (noYes.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }

    public static int playGame(int num, Scanner console) {
        System.out.println("I am thinking of a number between 1 and " + MAX + "...");
        int guess = -1;
        int totalGuesses = 0;
        while (guess != num) {
            System.out.print("Your guess? ");
            guess = console.nextInt();
            totalGuesses++;
            if (num < guess) {
                System.out.println("It's lower");
            } else if (num > guess) {
                System.out.println("It's higher");
            }
        }
        System.out.println();
        if (totalGuesses == 1) {
            System.out.println("You got it right in 1 guess!");
        } else {
            System.out.println("You got it right in " + totalGuesses + " guesses!");
        }
        return totalGuesses;
    }

    public static void printResults(int totalGames, int totalGuesses, int bestGame) {
        System.out.println("Overall Results:");
        System.out.println("Total games   = " + totalGames);
        System.out.println("Total guesses = " + totalGuesses);
        System.out.printf("Guesses/game  = %.1f", (float)totalGuesses / totalGames);
        System.out.println();
        System.out.print("Best game     = " + bestGame);
    }
}
