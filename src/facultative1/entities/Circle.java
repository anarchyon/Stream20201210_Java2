package facultative1.entities;

import java.awt.*;

public abstract class Circle {
    int x, y;
    int width, height;
    int speed;
    Color color;

    public Circle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
