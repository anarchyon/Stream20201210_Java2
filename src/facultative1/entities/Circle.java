package facultative1.entities;

import java.awt.*;

public abstract class Circle {
    int x, y;
    double dX, dY;
    int diameter;
    double speed;
    Color color;

    public Circle(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        x += dX * speed;
        y += dY * speed;
    }

    public void checkBounds(int width, int height) {
        int x2 = x + (int)(dX * speed);
        int y2 = y + (int)(dY * speed);
        if ((x2 - diameter / 2) < 0 || (x2 + diameter / 2) > width) {
            dX = -dX;
        }
        if ((y2 - diameter / 2) < 0 || (y2 + diameter / 2) > height) {
            dY = -dY;
        }
    }
}
