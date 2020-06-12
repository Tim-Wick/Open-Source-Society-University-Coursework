import java.util.*;

public class MyWumpusHunter extends WumpusHunter{
    private MountainCave lastCave;

    public String getName() {
        return "TimboSlice_WumpusHuterExtraordinaire";
    }

    public void startAt(MountainCave root) {
        // Intro section
        System.out.println("Hunter's name: " + getName());
        System.out.println("Hunter's report:");

        // Sets up a stack to keep track of the caves
        Stack<MountainCave> caveStack = new Stack<>();
        // Pushes root on to the stack
        caveStack.push(root);
        // Sets a trigger for when we have found the scales
        boolean caveHasScales = false;
        // Variable to keep track of the treasure room
        MountainCave currentCave = null;
        // Loops until we have found the scales
        while (!caveHasScales) {
            // Takes the cave off the top of the stack
            currentCave = caveStack.pop();
            // Prints the location
            printLocation(currentCave.getCaveName());
            // Checks if we are adjacent to the scales and prints the string if so
            if (currentCave.isAdjacentToScales()) {
                System.out.println("We've neared the scales!");
            }
            // Checks if we have reached the scales
            caveHasScales = currentCave.hasScales();
            // Creates an ArrayList of the currentCave's children
            ArrayList<MountainCave> children = currentCave.getChildren();

            // Possible implementation although it does not match the output given for BinaryMountainFactory in the
            // self assessment outline
//            // Looping backwards over children to process in order in which they are put in
//            for (int i = children.size() - 1; i >= 0; i--) {
//                caveStack.push(children.get(i));
//            }

            // Loops over the children of the currentCave
            for (MountainCave child : children) {
                // Pushes one onto the stack and we start the loop again
                caveStack.push(child);
            }
        }
        
        // Prints that we found the scales
        System.out.println("We've found the scales!");
        // Sets lastCave to where we were when we found the treasure
        lastCave = currentCave;
        // Prints out the report
        System.out.println(report());
    }

    // Function to create a string for the report
    // Takes a mountainCave to track the path
    public String report() {
        String reportString = "";
        Stack<MountainCave> pathStack = new Stack<>();

        // Pushes the path to the pathStack
        while (lastCave.hasParent()) {
            pathStack.push(lastCave);
            lastCave = lastCave.getParent();
        }
        reportString += "The path is...\nStart at the Mountain Top";
        // Adds the path
        while (!pathStack.isEmpty()) {
            reportString += " and\nthen visit the " + pathStack.pop().getCaveName();
        }
        return reportString;
    }

    // Helper function to print the location and avoid repeated code
    private void printLocation(String location) {
        System.out.println("Entering the " + location);
    }
}
