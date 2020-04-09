import java.awt.*;
import java.util.Random;

public class Lion extends Critter {

    private Color[] colorArr = {Color.RED, Color.GREEN, Color.BLUE};
    private Color currentColor;
    private Random r = new Random();
    private int moveIndicator = -1;

    public Action getMove(CritterInfo info) {
        moveIndicator++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }

    public Color getColor() {
        if (moveIndicator % 3 == 0) {
            currentColor = colorArr[r.nextInt(3)];
            return(currentColor);
        } else {
            return currentColor;
        }
    }

    public String toString() {
        return "L";
    }
}
