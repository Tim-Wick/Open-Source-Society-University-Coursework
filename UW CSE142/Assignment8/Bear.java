import java.awt.*;

public class Bear extends Critter {

    private boolean Polar;
    private int toStringReturn = -1;

    public Bear(boolean Polar) {
        this.Polar = Polar;
    }

    public Action getMove(CritterInfo info) {
        toStringReturn++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY){
            return Action.HOP;
        } else {
            return Action.LEFT;
        }
    }

    public Color getColor() {
        if (Polar) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public String toString() {
        if (toStringReturn % 2 == 0) {
            return "/";
        } else {
            return "\\";
        }
    }
}
