import java.awt.*;

public class Pipe extends Rectangle {

    int speed = 1;

    public Pipe(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
        move();
    }

}
