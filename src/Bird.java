import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird extends Rectangle {

    private int yVel;
    public static int jumpForce = 125;
    private static int gravityForce = 2;

    private boolean touchingBounds = false;


    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        if (!touchingBounds) {
            this.y -= jumpForce;
            System.out.println("Key pressed");
        }
    }

    public void move() {
        if (!touchingBounds) {
            y += gravityForce;
        }
    }

    public void setGravity(int gravity) {
        gravityForce = gravity;
    }

    public void touchingBounds(boolean value) {
        touchingBounds = value;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
        move();
    }

}
