import java.awt.*;

public class CafeWall {
    public static final int MORTAR = 2;

    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(650, 400);
        panel.setBackground(Color.GRAY);
        Graphics g = panel.getGraphics();
        drawRow(g,0,0,4,20);
        drawRow(g, 50, 70, 5, 30);
        drawGrid(g, 400, 20, 2, 35, 35);
        drawGrid(g, 10, 150, 4, 25, 0);
        drawGrid(g, 250, 200, 3, 25,10);
        drawGrid(g, 425, 180, 5, 20, 10);
    }

    public static void drawRow(Graphics g, int x, int y, int boxPairs, int boxSize) {
        for (int i = 0; i < boxPairs; i++) {
            g.setColor(Color.black);
            g.fillRect(x,y,boxSize,boxSize);
            g.setColor(Color.blue);
            g.drawLine(x, y, x + boxSize, y + boxSize);
            g.drawLine(x + boxSize, y, x, y + boxSize);
            g.setColor(Color.white);
            g.fillRect(x + boxSize, y, boxSize, boxSize);
            x += boxSize * 2;
        }
    }

    public static void drawGrid(Graphics g, int x, int y, int boxPairs, int boxSize, int offset) {
        for (int i = 0; i < boxPairs; i++) {
            drawRow(g, x, y, boxPairs, boxSize);
            drawRow(g, x + offset, y + boxSize + MORTAR, boxPairs, boxSize);
            y += (boxSize * 2 + MORTAR * 2);
        }
    }

}
