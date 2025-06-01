import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 800;
    private static final Dimension GAME_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    private static final int BIRD_SIZE = 50;

    Thread gameThread;
    Image img;
    Graphics graphics;

    Bird bird;

    Pipe[] pipes;
    int pipeCount = 0;

    int pipeWidth = 25;
    int pipeHeight = 80;

    int pipeXSpawn = GAME_WIDTH - 10;
    int pipeYSpawn = (GAME_HEIGHT / 2) - (pipeHeight / 2);






    public GamePanel() {
        newBird();
        newPipe();

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(GAME_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBird() {
        bird = new Bird(65, (GAME_WIDTH / 2) - BIRD_SIZE, BIRD_SIZE, BIRD_SIZE);
    }

    public void newPipe() {
        pipes = new Pipe[3];

        for (int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipe(pipeXSpawn, pipeYSpawn, pipeWidth, pipeHeight);
        }

        pipeCount = 0;
    }

    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();
        draw(graphics);
        g.drawImage(img, 0, 0, this);
    }

    public void draw(Graphics g) {
        bird.draw(g);
        pipes[pipeCount % pipes.length].draw(g);


    }

    public void move() {
        bird.move();
        pipes[pipeCount].move();
    }

    public void checkColl() {
        if (bird.y >= GAME_HEIGHT - BIRD_SIZE) {
            bird.y = GAME_HEIGHT - BIRD_SIZE;
            bird.touchingBounds(true);
            // TODO: gameLose/restartGame method
        }

        if (bird.y <= 0) {
            bird.y = 0;
            bird.touchingBounds(true);
            // TODO: gameLose/restartGame method
        }
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
                move();
                checkColl();
                repaint();
                delta--;

            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            bird.keyPressed(e);
        }
    }
}
