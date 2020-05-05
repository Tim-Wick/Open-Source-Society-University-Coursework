import java.util.*;

// Class to manage a game of hangman with a "cheating" computer
public class HangmanManager {

    // Initializing class variables
    private Set<String> wordSet = new TreeSet<>();
    private Set<Character> letterGuesses = new TreeSet<>();
    private int guesses = 0;
    private String pattern = "-";

    // Initializes the state of the game by testing conditions (handling exceptions comment) and initializing
    // the maxGuesses to the given max, and dictionarySet to a set of words of the given length
    HangmanManager(Collection<String> dictionary, int length, int max) {
        // Handles exceptions for dictionary of length less than 1 or 0 for max
        if (dictionary.size() < 1 || max < 0) {
            throw new IllegalArgumentException();
        }
        guesses = max;
        pattern = pattern.repeat(length);
        for (String word : dictionary) {
            if (word.length() == length) {
                wordSet.add(word);
            }
        }
    }

    // Returns the current set of words being used
    Set<String> words() {
        return wordSet;
    }

    // Returns the number of guesses the player has left
    int guessesLeft() {
        return guesses;
    }

    Set<Character> guesses() {
        return letterGuesses;
    }

    String pattern() {
        return pattern;
    }

    int record(char guess) {
        // Handles exceptions if there are no guesses left or words in the set
        if (guesses < 1 || wordSet.size() < 1) {
            throw new IllegalStateException();
        }
        // Handles exceptions if letter has already been guessed
        if (letterGuesses.contains(guess)) {
            throw new IllegalArgumentException();
        }
        // Build treemap off wordSet with sets of words relating to patterns
        Map <String, TreeSet<String>> patternMap = buildTreeMap(guess);
        // Update word set and pattern with patternMap and the pattern that occurs the highest number of times
        updateWords(patternMap);
        // Record guess
        letterGuesses.add(guess);
        // returns number of occurences of guess in new pattern
        int occurrences = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == guess) {
                occurrences++;
            }
        }
        if (occurrences == 0) {
            guesses--;
        }
        return occurrences;


    }

    // Method to find the words in wordSet with the lowest number of occurences of the guess and update wordSet with just those words
    private void updateWords(Map <String, TreeSet<String>> patternMap) {
        // Initializes the count of the pattern and the most common pattern
        int patternCount = 0;
        String mostCommonPattern = "";
        // Pulls a Set of keys from patternMap
        Set<String> allPatterns = patternMap.keySet();
        // Loop keys, get the values and see if there are more than the current max
        // If so, make the key the new max and update the count
        for (String key : allPatterns) {
            TreeSet<String> words = patternMap.get(key);
            if (words.size() > patternCount) {
                patternCount = words.size();
                mostCommonPattern = key;
            }
        }
        // Make the wordSet the words that made the most common pattern
        pattern = mostCommonPattern;
        wordSet = patternMap.get(mostCommonPattern);
    }

    // Builds a map of patterns with their related strings as TreeSets
    // Takes the user guess
    private Map<String, TreeSet<String>> buildTreeMap(char guess) {
        Map <String, TreeSet<String>> workingMap = new TreeMap<>();
        // Loops over the words that are in word set
        for (String word : wordSet) {
            // Replace "-" with guess in word
            // Convert to a charArray for easier modification
            char[] patternCopy = pattern.toCharArray();
            // Loops over the length of the word and if the character matches the guess, adds the character to the pattern
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    patternCopy[i] = guess;
                }
            }
            // Convert back to a string to be able to add to the set
            String patternCopyString = new String (patternCopy);
            // Match it to other patterns and/or place in map
            // Initialize the set to add
            TreeSet<String> toAddSet = new TreeSet<>();
            if (workingMap.containsKey(patternCopyString)) {
                // If the map already contains the key, we need a copy of the values
                toAddSet = workingMap.get(patternCopyString);
            }
            // Add the word to either the blank TreeSet if the pattern did not exist before, or add it to the existing set
            toAddSet.add(word);
            // Put the set into the map
            workingMap.put(patternCopyString, toAddSet);
        }
        return workingMap;
    }

}
