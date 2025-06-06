import java.awt.*;

public class GameOverScreen extends Rectangle {

    private static int GW;
    private static int GH;
    private boolean gameOver = false;

    public GameOverScreen(int w, int h) {
        GW = w;
        GH = h;
    }

    public void draw(Graphics g) {
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER", (GW / 2) - 100, (GH / 2));
            g.drawString("PRESS 1 TO PLAY AGAIN", (GW / 2) - 250, (GH / 2) + 50);
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean getGameOver() {
        return gameOver;
    }



}
