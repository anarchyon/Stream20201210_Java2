package facultative1.entities;

import java.awt.*;

public abstract class Circle {
    int x, y;
    int diameter;
    int speed;
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
}
