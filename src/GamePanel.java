import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 800;
    private static final Dimension GAME_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    private static final int BIRD_SIZE = 25;

    Thread gameThread;
    Image img;
    Graphics graphics;


    public GamePanel() {

        this.setFocusable(true);
        this.setPreferredSize(GAME_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();
        draw(graphics);
        g.drawImage(img, 0, 0, this);
    }

    public void draw(Graphics g) {

    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;



        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;


            if (delta >= 1) {
                // move();
                // checkColl();
                repaint();
                delta--;

            }
        }
    }
}
