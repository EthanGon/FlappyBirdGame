import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird extends Rectangle {


    private static int gravityForce = 2;

    private boolean touchingBounds = false;
    public boolean canJump = true;

    boolean isJumping = false;
    int lastY;

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void jump(KeyEvent e) {
        if (!touchingBounds && canJump && e.getKeyCode() == KeyEvent.VK_SPACE) {
            //this.y -= jumpForce;
            isJumping = true;
            lastY = y;
        }
        canJump = false;
    }

    public void jumpReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
           canJump = true;
        }
    }

    public void move() {

        /*
        when player presses "Space", isJumping == false, and stores the y in lastY to use later,
        then make the force negative, to go up a bit

        then when the player's current y is more than (technically less than but w.e) player's lastY plus a certain amount
        then start dropping again, kinda a way to simulate gravity
        * */

        if (isJumping) {
            gravityForce = -2;
        }

        if (y <= lastY - 100) {
            isJumping = false;
            gravityForce = 2;
        }

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
