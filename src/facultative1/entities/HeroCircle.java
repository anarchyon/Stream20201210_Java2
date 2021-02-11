package facultative1.entities;

import facultative1.CircleParams;

public class HeroCircle extends Circle {

    public HeroCircle(int centerX, int centerY) {
        super(centerX - CircleParams.HERO_NOMINAL_DIAMETER / 2, centerY - CircleParams.HERO_NOMINAL_DIAMETER / 2,
                CircleParams.HERO_NOMINAL_DIAMETER);
        color = CircleParams.HERO_COLOR;
    }

    public void move(int toX, int toY) {
        x += (toX - x) * CircleParams.HERO_SPEED;
        y += (toY - y) * CircleParams.HERO_SPEED;
    }
}
