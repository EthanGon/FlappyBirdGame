import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 800;
    private static final Dimension GAME_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    private static final int BIRD_SIZE = 25;

    private Thread gameThread;
    private Image img;
    private Graphics graphics;
    private Random random;

    private Bird bird;
    private Pipe pipe;

    private Score score;
    private final GameOverScreen gameOverScreen;

    private final int PIPE_WIDTH = 50;
    private final int PIPE_HEIGHT = 230;

    public Sound scoreSound = new Sound();


    public GamePanel() {
        newPipe();
        newBird();
        newScore();

        scoreSound.setFile(0);
        gameOverScreen = new GameOverScreen(GAME_WIDTH, GAME_HEIGHT);

        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(GAME_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void restartGame() {
        newPipe();
        newBird();
        newScore();
        gameOverScreen.setGameOver(false);
    }

    public void newScore() {
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
    }

    public void newBird() {
        bird = new Bird(65, (GAME_WIDTH / 2) - BIRD_SIZE, BIRD_SIZE, BIRD_SIZE);
    }

    public void newPipe() {
        random = new Random();

        // randomize the pipe gap to be between game-height -/+ 175
        int low = (GAME_HEIGHT / 2) - (PIPE_HEIGHT / 2) - 175;
        int high = (GAME_HEIGHT / 2) - (PIPE_HEIGHT / 2) + 175;
        int randomY = random.nextInt(high - low) + low;

        pipe = new Pipe((GAME_WIDTH + 50), randomY, PIPE_WIDTH, PIPE_HEIGHT, Color.BLACK);

    }

    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();
        draw(graphics);
        g.drawImage(img, 0, 0, this);
    }

    public void draw(Graphics g) {
        pipe.draw(g);
        score.draw(g);
        bird.draw(g);

        // TODO: only draw when player has died
        gameOverScreen.draw(g);
    }

    public void move() {
        bird.move();
        pipe.move();
        pipe.topPipe.move();
        pipe.bottomPipe.move();
    }

    public void checkColl() {
        if (bird.y >= GAME_HEIGHT - BIRD_SIZE) {
            bird.touchingBounds(true);
            gameOverScreen.setGameOver(true);
        }

        if (bird.y <= 0) {
            bird.touchingBounds(true);
            gameOverScreen.setGameOver(true);
        }

        // if pipe is out of bounds, spawn new pipe
        if (pipe.x <= -50) {
            newPipe();
        }

        if (bird.intersects(pipe.topPipe) || bird.intersects(pipe.bottomPipe)) {
            gameOverScreen.setGameOver(true);
            bird.canJump = false;
        } else if (bird.intersects(pipe) && !pipe.scored) {
            pipe.scored = true;
            scoreSound.play();
            scoreSound.setFile(0);
            score.addScore();
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
            if (!gameOverScreen.getGameOver()) {
                bird.jump(e);
            }

            if (e.getKeyCode() == KeyEvent.VK_1 && gameOverScreen.getGameOver()) {
                restartGame();
            }

        }

        public void keyReleased(KeyEvent e) {
            if (!gameOverScreen.getGameOver()) {
                bird.jumpReleased(e);
            }
        }
    }
}
