import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird extends Rectangle {

    public static int jumpForce = 125;
    private static int gravityForce = 2;

    private boolean touchingBounds = false;
    public boolean canJump = true;

    public int score = 0;


    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void jump(KeyEvent e) {
        if (!touchingBounds && canJump && e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.y -= jumpForce;
            System.out.println("Key pressed");
        }
        canJump = false;
    }

    public void jumpReleased(KeyEvent e) {
        canJump = true;
    }

    public void move() {
        y += gravityForce;
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
