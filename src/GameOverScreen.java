import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScreen extends Rectangle {

    static int GW;
    static int GH;
    boolean gameOver = false;

    public GameOverScreen(int w, int h) {
        GW = w;
        GH = h;
    }

    public void draw(Graphics g) {
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", (GW / 2) - 100, (GH / 2));
            g.drawString("PRESS 1 TO PLAY AGAIN", (GW / 2) - 185, (GH / 2) + 50);
        }
    }



}
