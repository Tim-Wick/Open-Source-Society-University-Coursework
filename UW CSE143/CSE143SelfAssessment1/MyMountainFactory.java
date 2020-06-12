import java.util.*;

public class MyMountainFactory extends MountainFactory {
    // Class constant for the number of nodes in the tree
    private static final int SIZE = 30;
    Random scalePicker = new Random();

    // Sets up lists for a "random" name generator later on
    private String[] firstWord = new String[] {"Clover", "Crooked", "Rocky", "Bare", "Bright", "Dark", "Clean", "Dirty", "Cosy", "Comfortable", "Tidy", "Lofty", "Large",
            "Simple", "Dusty", "Tiny", "Pink", "Blue", "Red", "Orange", "Purple", "Sparse", "Cold", "Hot", "Muffled", "Shiny", "Circular", "Pleasant"};
    private String[] secondWord = new String[] {"Room", "Cave", "Chamber", "Cove", "Way", "Nook", "Path", "Pass", "Den", "Suite", "Vault", "Niche",
            "Foyer", "Gallery", "Hall", "Leeway", "Rotunda", "Theater"};

    // Function to get the mountainTop
    public MountainCave getMountainTop () {
        // Initializes a variable to keep track of the treasure room
        int treasureRoom = 0;
        // Picks a random number for the treasure room, making sure it is not 0 or 1
        while (treasureRoom == 0 || treasureRoom == 1) {
            treasureRoom = scalePicker.nextInt(SIZE);
        }
        // Builds the cave system and returns
        return buildMountain(1, SIZE, null, treasureRoom);
    }

    // Recursive function to build the tree with an arbitrary number of nodes
    private MountainCave buildMountain(int caveNumber, int max, MountainCave parent, int treasureRoom) {
        // int caveNumber
        if (caveNumber > max) {
            // Base case is exceeding the max in which case we reached the end and can return
            return null;
        } else {
            String caveName;
            // Picks a name for the cave
            if (caveNumber == 1) {
                caveName = "Mountain Top";
            } else {
                caveName = nameGenerator();
            }
            // Builds a newCave and attaches the previous cave as parent
            MountainCave newCave = new MountainCave(parent, caveName, "You are in cave #" + caveNumber);
            // Sets up left and right (first/second) children nodes
            MountainCave firstChild = buildMountain(2 * caveNumber, max, newCave, treasureRoom);
            MountainCave secondChild = buildMountain(2 * caveNumber + 1, max, newCave, treasureRoom);

            // Checks if this is where we should put the scales
            // If so adds scales to this cave and adjacent to scales in the parent
            if (caveNumber == treasureRoom) {
                newCave.setHasScales(true);
                parent.setAdjacentToScales(true);
            }
            // Returns the cave
            return newCave;
        }
    }

    // Function to return a random name that makes the caves a bit more interesting than just numbers
    private String nameGenerator() {
        return (firstWord[scalePicker.nextInt(firstWord.length)] + " " + secondWord[scalePicker.nextInt(secondWord.length)]);
    }
}
