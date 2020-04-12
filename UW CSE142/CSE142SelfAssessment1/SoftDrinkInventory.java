import java.util.*;

// Class to process inventory files and transaction files, returning a final report of the inventory and transactions
public class SoftDrinkInventory {

    //Initializing fields
    private String[] softDrinkName = new String[100];
    private String[] softDrinkID = new String[100];
    private int[] startInventory = new int[100];
    private int[] finalInventory = new int[100];
    private int[] transactionCounts = new int[100];

    // Initializes arrays to appropriate zero values
    public SoftDrinkInventory() {
        initializeString(softDrinkName);
        initializeString(softDrinkID);
        initializeInt(startInventory);
        initializeInt(finalInventory);
        initializeInt(transactionCounts);
    }

    // Takes an inventory file and builds the name, id, and inventory arrays
    public void buildInventory(Scanner inventoryFile) {
        int tracker = 0;
        while (inventoryFile.hasNextLine()) {
            String[] processLine = inventoryFile.nextLine().split(" ");
            softDrinkName[tracker] = processLine[0];
            softDrinkID[tracker] = processLine[1];
            startInventory[tracker] = Integer.parseInt(processLine[2]);
            tracker++;
        }
        finalInventory = Arrays.copyOf(startInventory, 100);
    }

    // Takes a transactions file and applies the information to the final inventory and keeps track of # of transactions
    public void processTransactions(Scanner transactionsFile) {
        while (transactionsFile.hasNextLine()) {
            String[] transactionLine = transactionsFile.nextLine().split(" ");
            int drinkPos = findID(transactionLine[0]);
            if (drinkPos != -1) {
                finalInventory[drinkPos] += Integer.parseInt(transactionLine[1]);
                transactionCounts[drinkPos]++;
            }
        }
    }

    // Displays report of drinks, IDs, starting inventory, final inventory, and # of transactions
    public void displayReport() {
        System.out.printf("%-15s %-5s %20s %20s %20s\n", "Soft Drink", "ID", "Starting Inventory", "Final Inventory", "# transactions");
        for (int y = 0; y < 100; y++) {
            if (!softDrinkName[y].equals("")) {
                System.out.printf("%-15s %-5s %11d %22d %20d\n", softDrinkName[y], softDrinkID[y], startInventory[y],
                        finalInventory[y], transactionCounts[y]);
            }
        }
    }

    // Takes an ID string and returns the index of the ID in the ID array, or returns -1 if it is not found
    public int findID(String ID) {
        for (int x = 0; x < softDrinkID.length; x++) {
            if (softDrinkID[x].equals(ID)) {
                return x;
            }
        }
        return -1;
    }

    // Initializes given string array to nulls
    private static void initializeString(String[] givenString) {
        Arrays.fill(givenString, "");
    }

    // Initializes given int array to zeros
    private static void initializeInt(int[] givenInt) {
        Arrays.fill(givenInt, 0);
    }
}
