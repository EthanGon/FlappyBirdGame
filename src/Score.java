import java.awt.*;

public class Score extends Rectangle {

    int w;
    int h;
    int score;

    public Score(int width, int height) {
        w = width;
        h = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));

        g.drawString("Score: " + score, 50, 50);
    }

}
