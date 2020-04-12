import java.util.*;

// Class to play a hangperson style game
public class HangPerson {

    // Initializing fields
    private ArrayList<String> wordArray = new ArrayList<>();
    private Random r = new Random();

    // Constructor. Sets up arrayList of words from given file
    public HangPerson(Scanner wordFile) {
        getWordArray(wordFile);
    }

    // Displays game intro
    public void displayGameIntro() {
        System.out.println("Welcome to the hangperson style game ... \n" +
                "To play, guess a letter to try to guess the word.\n" +
                "You start with seven flowers. Every time you choose an incorrect letter,\n" +
                "a flower dies. If you guess the word before all the flowers are dead,\n" +
                "you win :-) If all the flowers die, you lose :-(\n\nTime to guess...");
    }

    // Plays a game
    public void play() {
        // Gets a scanner for gathering user input
        Scanner userInput = new Scanner(System.in);
        // Gets a word from the ArrayList of words from the word file
        char[] gameWord = getGameWord();
        // Makes a copy of the word and makes all characters "-"
        char[] wordSoFar = Arrays.copyOf(gameWord, gameWord.length);
        Arrays.fill(wordSoFar, '-');
        // Sets up a boolean array to track letter guess and fills with false values
        boolean[] tracker = new boolean[26];
        Arrays.fill(tracker, false);
        // Initializes wrong guesses
        int wrongGuesses = 0;
        // Initializes a userGuess character
        char userGuess;

        // Displays initial game state
        displayGame(tracker, wrongGuesses, wordSoFar);

        // Checks if user has guessed word or has hit their 7 guesses
        while(!Arrays.equals(gameWord, wordSoFar) && wrongGuesses < 7) {
            // Gets a guess from the user
            userGuess = getUserInput(userInput, tracker);
            boolean correctGuess = checkGuess(gameWord, userGuess, wordSoFar);
            if (!correctGuess) {
                wrongGuesses++;
            }
            // Displays gamestate
            displayGame(tracker, wrongGuesses, wordSoFar);
        }

        if (wrongGuesses == 7) {
            System.out.println("Too bad, you lose!");
            System.out.print("The word was ==> ");
            for (char letter : gameWord) {
                System.out.print(letter);
            }
            System.out.println();
        } else {
            System.out.println("Congratulations, you win!!!");
        }
    }

    // Checks to see if guess is in the word. Updates wordsSoFar if it is and returns true, else returns false
    private boolean checkGuess(char[] word, char guess, char[] wordSoFar) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] == guess) {
                wordSoFar[i] = guess;
                return true;
            }
        }
        return false;
    }

    // Prompts for a checks user input, making sure it is a char and has not already bee guessed
    private char getUserInput(Scanner userInput, boolean[] guessTracker) {
        System.out.print("Choose a letter => ");
        char guess = userInput.next().charAt(0);
        while (!Character.isLetter(guess) || (guessTracker[guess - 'a'])) {
            if (!Character.isLetter(guess)) {
                System.out.println("Invalid, don't you know your ABCs?");
            } else if (guessTracker[guess - 'a']) {
                System.out.println("You already tried this letter");
            }
            System.out.print("Choose a letter => ");
            guess = userInput.next().toLowerCase().charAt(0);
        }
        guessTracker[guess - 'a'] = true;
        return guess;
    }

    // Turns the given scanner wordFile into a reusable arrayList
    // No default list given so method handles files of various length
    private void getWordArray(Scanner wordFile) {
        while (wordFile.hasNextLine()) {
            wordArray.add(wordFile.nextLine());
        }
    }

    // Gets a word from the word Array
    private char[] getGameWord() {
        return wordArray.get(r.nextInt(wordArray.size())).toCharArray();
    }

    // Displays the number of alive flowers and current game stats
    private void displayGame(boolean[] guessTracker, int num, char[] guessed) {
        System.out.println();
        int alive = 7 - num;
        for (int i = 0; i < alive; i++) {
            System.out.print(" |\\/\\/|   ");
        }
        System.out.println();
        for (int i = 0; i < alive; i++) {
            System.out.print(" \\    /   ");
        }
        System.out.println();
        for (int i = 0; i < alive; i++) {
            System.out.print("  \\  /    ");
        }
        System.out.println();
        for (int i = 0; i < alive; i++) {
            System.out.print("   \\/     ");
        }
        System.out.println();
        for (int i = 0; i < alive; i++) {
            System.out.print("   |      ");
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print("\\  |  /   ");
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(" \\ | /    ");
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print("  \\|/     ");
        }
        System.out.println();
        System.out.println();

        System.out.print("Letters guessed already => ");
        for (int i = 0; i < 26; i++) {
            if (guessTracker[i]) {
                System.out.print((char)(i + 97) + " ");
            }
        }
        System.out.println();
        System.out.println("Number of wrong guesses => " + num);
        System.out.print("The word so far => ");
        for (char guess : guessed) {
            System.out.print(guess);
        }
        System.out.println();
        System.out.println();
    }
}

