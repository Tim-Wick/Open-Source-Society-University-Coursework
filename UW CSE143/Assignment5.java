import java.util.*;

// This class generates random sentences based off of given grammars
public class GrammarSolver {

    private SortedMap<String, String> grammarMap = new TreeMap<>();

    // Constructor fed a grammar as a list of string
    GrammarSolver(List<String> grammar) {
        // Handles exceptions for empty grammars
        if (grammar.size() == 0) {
            throw new IllegalArgumentException();
        }
        // Set to test for duplicates
        Set<String> testDuplicateSet = new TreeSet<>();
        // Loops list and adds entries to the map
        // Also handles exceptions of entries are repeated
        for (String string : grammar) {
            String[] splitString = string.split("::=");
            if (!testDuplicateSet.add(splitString[0])) {
                throw new IllegalArgumentException();
            }
            grammarMap.put(splitString[0], splitString[1]);
        }
    }

    // Returns true if the map contains the key and false if it does not
    boolean grammarContains(String symbol) {
        return grammarMap.containsKey(symbol);
    }

    // Returns the symbols of the grammar
    String getSymbols() {
        return grammarMap.keySet().toString();
    }

    String[] generate(String symbol, int times) {
        // Handles exceptions for 0 times or if the symbol is not included in the grammar
        if (!grammarContains(symbol) || times < 0) {
            throw new IllegalArgumentException();
        }
        String[] returnArr = new String[times];
        for (int i = 0; i < times; i++) {
            returnArr[i] = generatePriv(symbol).trim();
        }
        return returnArr;
    }

    private String generatePriv(String symbol) {
        Random randomInt = new Random();
        String returnString = "";
        // choose random value for given key, if key is non terminal, call generate priv on it, else add to string
        String[] ruleArr = grammarMap.get(symbol).split("[|]");
        String[] chosenRule = ruleArr[randomInt.nextInt(ruleArr.length)].split("[ \\t]+");
        for (String rule : chosenRule) {
            // Base case. If rule is not a non-terminal, return the rule
            if (!rule.equals("")) {
                if (!grammarContains(rule)) {
                    returnString = returnString + rule + " ";
                    // Recursive case. If rule is a non-terminal, call generatePriv with the rule
                } else {
                    returnString = returnString + generatePriv(rule);
                }
            }
        }
        return returnString;
    }
}
