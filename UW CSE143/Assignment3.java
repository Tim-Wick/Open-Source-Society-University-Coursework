import java.util.*;

// Class for playing the game of Assassin
public class AssassinManager {

    private AssassinNode killRingFront = new AssassinNode("");
    private AssassinNode graveyardFront = new AssassinNode("");
    private int killRingSize = 0;

    // Constructor for the class, takes a list of names and constructs AssassinNodes for each
    AssassinManager(List<String> names) {
        if (names.size() <= 0) {
            throw new IllegalArgumentException();
        }
        killRingSize = names.size();
        AssassinNode killRingLast = new AssassinNode("");
        int last = names.size() - 1;
        for (int i = last; i >= 0; i--) {
            killRingFront = new AssassinNode(names.get(i), killRingFront);
            if (i == last) {
                killRingLast = killRingFront;
            }
        }
        killRingLast.next = killRingFront;
    }

    // Prints the current kill ring
    void printKillRing() {
        AssassinNode current = killRingFront;
        for (int i = 0; i < killRingSize; i++) {
            System.out.println("    " + current.name + " is stalking " + current.next.name);
            current = current.next;
        }
    }

    // Prints the current graveyard
    void printGraveyard() {
        AssassinNode current = graveyardFront;
        while (current.next != null) {
            System.out.println("    " + current.name + " was killed by " + current.killer);
            current = current.next;
        }
    }

    // Checks if the kill ring contains the given name
    boolean killRingContains(String name) {
        AssassinNode current = killRingFront;
        for (int i = 0; i < killRingSize; i++) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Checks if the graveyard contains the given name
    boolean graveyardContains(String name) {
        AssassinNode current = graveyardFront;
        while (current.next != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Checks if there is only one person left in the kill ring
    boolean gameOver() {
        return killRingFront.name.equals(killRingFront.next.name);
    }

    // Returns the name of the winner if the game is over, else returns null
    String winner() {
        if (gameOver()) {
            return killRingFront.name;
        } else {
            return null;
        }
    }

    // Kills a player
    void kill(String name) {
        if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        } else if (gameOver()) {
            throw new IllegalStateException();
        }

        AssassinNode current = killRingFront;
        while (!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
        }
        if (current.next == killRingFront) {
            killRingFront = current;
        }
        current.next.killer = current.name;
        AssassinNode temp = current.next;
        current.next = current.next.next;
        temp.next = graveyardFront;
        graveyardFront = temp;
        killRingSize--;
    }
}
