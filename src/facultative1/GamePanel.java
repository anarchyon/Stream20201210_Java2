package facultative1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GamePanel extends JPanel {
    private int startX;
    private int startY;
    private int needGoX;
    private int needGoY;

    public GamePanel () {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                needGoX = e.getX();
                needGoY = e.getY();
            }
        });
    }

    private void circleMove(Graphics g) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        circleMove(g);
        repaint();
    }
}
