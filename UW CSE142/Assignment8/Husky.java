import java.awt.*;

public class Husky extends Critter {

    public Action getMove(CritterInfo info) {
        if (info.getFront() == Neighbor.OTHER || info.getBack() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }
    }

    public Color getColor() {
        return Color.LIGHT_GRAY;
    }

    public String toString() {
        return "Bork";
    }
}
