import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird extends Rectangle {

    private int yVel;
    private static final int jumpForce = 10;
    private static final int gravityForce = 2;


    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

        }
    }

    public void move() {
        this.y += gravityForce;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
        move();
    }

}
