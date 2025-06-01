import java.awt.*;

public class Pipe extends Rectangle {

    int speed = 1;
    Color color;
    TBPipe topPipe;
    TBPipe bottomPipe;

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
