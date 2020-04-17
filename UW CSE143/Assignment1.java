// Class to keep track of an inventory of letters from a given string
public class LetterInventory {
    private static final int INVENTORY_SIZE = 26;

    private int[] inventory = new int[INVENTORY_SIZE];
    private int size;

    // Constructor that takes a string and creates an inventory of the letters while keeping track of the size of the inventory
    // Ignores case and non-alphabetic characters
    public LetterInventory(String data) {
        char[] dataToChar = data.toLowerCase().toCharArray();
        for (char character : dataToChar) {
            if (Character.isLetter(character)) {
                inventory[character - 'a'] ++;
                size++;
            }
        }
    }

    // Returns count of given letter in the inventory
    // Throws illegal argument exception if it is given something other than a char
    public int get(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException();
        }
        return inventory[Character.toLowerCase(letter) - 'a'];
    }

    // Sets the occurrences of the given letter to the given value
    // Throws illegal argument exception if it is given something other than a char or a negative value
    public void set(char letter, int value) {
        if (!Character.isLetter(letter) || value < 0) {
            throw new IllegalArgumentException();
        }
        int inventoryPosition = Character.toLowerCase(letter) - 'a';

        size += value - inventory[inventoryPosition];
        inventory[inventoryPosition] = value;
    }

    // Returns the size of the inventory
    public int size() {
        return size;
    }

    // Returns true or false to indicate if the inventory is empty
    public boolean isEmpty() {
        return (size == 0);
    }

    // Returns a string representation of the inventory
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[";
            for (int i = 0; i < INVENTORY_SIZE; i++) {
                for (int j = 0; j < inventory[i]; j++) {
                    result += (char)('a' + i);
                }
            }
            result += "]";
            return result;
        }
    }

    // Returns a new LetterInventory consisting of the current LetterInventory added to the given LetterInventory
    public LetterInventory add(LetterInventory other) {
        LetterInventory inventoryToReturn = new LetterInventory("");
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            inventoryToReturn.set((char) ('a' + i), inventory[i] + other.get((char) ('a' + i)));
        }
        return inventoryToReturn;
    }

    // Returns a new LetterInventory consisting of the current LetterInventory subtracted from the given LetterInventory
    // Returns null if any new value would be negative
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory inventoryToReturn = new LetterInventory("");
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            int newValue = inventory[i] - other.get((char) ('a' + i));
            if (newValue < 0) {
                return null;
            } else {
                inventoryToReturn.set((char) ('a' + i), newValue);
            }
        }
        return inventoryToReturn;
    }

}
