package facultative1;

import facultative1.entities.Circle;
import facultative1.entities.HeroCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GamePanel extends JPanel {
    private int needGoX;
    private int needGoY;
    Manager manager;

    private Circle hero;

    public GamePanel () {
        manager = new Manager(this);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                manager.go(e.getX(), e.getY());
            }
        });
        setBackground(Color.gray);
    }

    public void drawCircle(Graphics g, Circle circle) {
        g.fillOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        repaint();
    }

    private void draw(Graphics g) {
        drawCircle(g, manager.getHero());
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
