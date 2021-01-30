package lesson1.homework1.obstacles;

import lesson1.homework1.common.DefaultParameters;

public class Wall extends Obstacle {

    public Wall() {
        super(DefaultParameters.OBSTACLE_AVERAGE_HEIGHT, DefaultParameters.OBSTACLE_HEIGHT_SPREAD);
        type = DefaultParameters.OBSTACLE_TYPE_CAN_BE_JUMPED_OVER;
    }

    public Wall(double height) {
        super();
        this.obstacleSize = height;
    }

    @Override
    public String toString () {
        return String.format("Стена высотой %.2fм", obstacleSize);
    }
}
