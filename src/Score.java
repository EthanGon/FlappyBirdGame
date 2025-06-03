import java.awt.*;
import java.net.URL;

public class Score extends Rectangle {

    private int w;
    private int h;
    private int score;

    Font font;

    public Score(int width, int height) {
        w = width;
        h = height;


        try {
            URL fontURL = getClass().getResource("/font/font.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        font = font.deriveFont(25.0f);

    }

    public void addScore() {
        score++;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Score: " + score, 50, 50);
    }

}
