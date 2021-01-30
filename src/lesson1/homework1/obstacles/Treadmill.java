package lesson1.homework1.obstacles;

import lesson1.homework1.common.DefaultParameters;

public class Treadmill extends Obstacle{

    public Treadmill() {
        super(DefaultParameters.AVERAGE_DISTANCE, DefaultParameters.DISTANCE_SPREAD);
        type = DefaultParameters.OBSTACLE_TYPE_CAN_BE_RUN;
    }

    public Treadmill(double distance) {
        super();
        this.obstacleSize = distance;
    }


    @Override
    public String toString() {
        return String.format("Дорожка длиной %.2fм", obstacleSize);
    }
}
