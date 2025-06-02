import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame() {
        gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Flappy Bird");
        this.setResizable(false);
        this.setBackground(Color.BLACK);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
