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
        gamePanel = new GamePanel();

        setVisible(true);
    }


}
