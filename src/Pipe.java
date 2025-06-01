import java.awt.*;

public class Pipe extends Rectangle {

    private final int speed = 2;
    Color color;
    TBPipe topPipe;
    TBPipe bottomPipe;

    // using this bool var b/c if player is still in safe zone it keeps increasing score
    public boolean scored = false;

    public Pipe(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;

        topPipe = new TBPipe(x, y+height, width, height * 2, Color.GREEN);
        bottomPipe = new TBPipe(x, y-height * 2, width, height * 2, Color.GREEN);

    }

    public void move() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        move();
        topPipe.draw(g);
        bottomPipe.draw(g);
    }

    public class TBPipe extends Rectangle {

        Color color;

        public TBPipe(int x, int y, int width, int height, Color color) {
            super(x, y, width, height);
            this.color = color;
        }

        public void move() {
            x -= speed;
        }

        public void draw(Graphics g) {
            g.setColor(this.color);
            g.fillRect(x, y, width, height);
            move();
        }


    }

}
