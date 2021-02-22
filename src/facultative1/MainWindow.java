package facultative1;

import javax.swing.*;

public class MainWindow extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    GamePanel gamePanel;

    public MainWindow () {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("RGBCircles");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gamePanel = new GamePanel(WIDTH, HEIGHT);
        add(gamePanel);
        setVisible(true);

    }


}
