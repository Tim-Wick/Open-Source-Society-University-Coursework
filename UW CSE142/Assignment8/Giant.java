import java.awt.*;

public class Giant extends Critter {

    private int moveIndicator = -1;
    private int speakIndicator = 0;
    private String[] speakArr= {"fee", "fie", "foe" ,"fum"};
    private String speak = "fee";

    public Action getMove(CritterInfo info) {
        moveIndicator++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY){
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }

    public Color getColor() {
        return Color.DARK_GRAY;
    }

    public String toString() {
        if (speakIndicator == 3) {
            speakIndicator = -1;
        }
        if (moveIndicator % 6 == 0) {
            speakIndicator++;
            speak = speakArr[speakIndicator];
            return speak;
        } else {
            return speak;
        }
    }
}
