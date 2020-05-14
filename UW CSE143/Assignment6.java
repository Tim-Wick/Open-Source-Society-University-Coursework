import java.util.*;

public class AnagramSolver {

    private Map<String, LetterInventory> wordMap = new HashMap<>();
    private List<String> wordList = new ArrayList<>();

    // Constructor that takes a list of strings as a dictionary
    // Places the resulting LetterInventories into a map and makes a copy of the list for later use
    AnagramSolver(List<String> list) {
        for (String word : list) {
            wordMap.put(word, new LetterInventory(word));
        }
        wordList.addAll(list);
    }

    // Function to recursively print anagrams of the givenString with a max amount of words (no max if max is 0)
    public void print(String givenString, int max) {
        // Handles exception for max being less than zero
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        // Initializes the givenString into a LetterInventory and initializes a blank array for printing
        LetterInventory phrase = new LetterInventory(givenString);
        List<String> printedWords = new ArrayList<>();
        // Calls the private recursive method feeding it the phrase, blank array, list of words, and a maximum
        printPrivate(phrase, printedWords, wordList, max);
    }

    // Private function to actually to the recursion and printing of anagrams of a given string
    // Takes a phrase as a LetterInventory, an array to store values and print, a list of potential words, and a max
    private void printPrivate(LetterInventory phrase, List<String> arrayToPrint, List<String> wordList, int max) {
        // Base case: the phrase is empty, so we have found an anagram. Print it!
        if (phrase.isEmpty()) {
            System.out.println(arrayToPrint);
        } else {
            // Recursive case: Loops through all words in the wordList
            // On each call, prune the given wordArray based on the current phrase so we pare down to only the possible words
            List<String> newWordList = pruneDictionary(phrase, wordList);
            for (String word : newWordList) {
                // Initializes the word to the corresponding LetterInventory to avoid multiple calls to wordMap
                LetterInventory currentInventory = wordMap.get(word);
                // Checks for dead ends: the phrase does not contain the next word
                // and either the max is 0 or we are less than the max
                if (phrase.subtract(currentInventory) != null && (max == 0 || arrayToPrint.size() < max)) {
                    // Makes the choice by adding the word to the array and subtracting from the phrase
                    arrayToPrint.add(word);
                    phrase = phrase.subtract(currentInventory);
                    // Recursively calls itself with the rest of the phrase, the array so far, and pared down word list, and the max
                    printPrivate(phrase, arrayToPrint, newWordList, max);
                    // Undoes the choice by adding the word back to the phrase and removing it from the array to print
                    phrase = phrase.add(currentInventory);
                    arrayToPrint.remove(word);
                }
            }
        }
    }

    // Helper function to prune the dictionary
    // Given a phrase to compare to words in the wordList
    // Returns a new list minus the words that are not included in the phrase
    private List<String> pruneDictionary(LetterInventory phrase, List<String> givenList) {
        List<String> toReturnList= new ArrayList<>();
        for (String word : givenList) {
            if (phrase.subtract(wordMap.get(word)) != null) {
                toReturnList.add(word);
            }
        }
        return toReturnList;
        }
}
