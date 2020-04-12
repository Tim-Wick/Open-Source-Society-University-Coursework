/**
 * This program tests the functionality of the SoftDrinkInventory class.
 * An object is constructed which initially holds no real data.
 * A datafile containing initial data is used to fill the SoftDrink object.
 * Then transactions are processed where each transaction contains how
 * cases are bought or sold. A function displays a report of the drink name,
 * ID, starting inventory, final inventory, and the number of transactions
 * processed.
 */
import java.util.Scanner; import
        java.io.FileInputStream; import
        java.io.FileNotFoundException;
public class SoftDrinkTester {
    public static void main (String[] args) {
        Scanner inventoryFile = null; // inventory data file
        Scanner transFile = null; // transaction data file
        // open the inventory initialization file
        try {
            inventoryFile = new Scanner(new FileInputStream("data6.txt"));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found or not opened.");
            System.exit(0);
        }
        // open the file containing the buy/sell transactions
        try {
            transFile = new Scanner(new FileInputStream("data6trans.txt"));
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found or not opened.");
            System.exit(0);
        }
        // instantiate the soft drink distributorship object
        // and process the transactions by updating the inventory totals
        SoftDrinkInventory softDrinks = new SoftDrinkInventory();
        softDrinks.buildInventory(inventoryFile);
        softDrinks.processTransactions(transFile);
        softDrinks.displayReport();
    }
}
