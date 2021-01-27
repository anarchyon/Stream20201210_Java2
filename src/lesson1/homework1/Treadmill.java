package lesson1.homework1;

public class Treadmill extends Obstacle{

    Treadmill() {
        super(DefaultParameters.AVERAGE_DISTANCE, DefaultParameters.DISTANCE_SPREAD);
        type = DefaultParameters.OBSTACLE_TYPE_CAN_BE_RUN;
    }

    Treadmill(double distance) {
        super();
        this.obstacleSize = distance;
    }


    @Override
    public String toString() {
        return String.format("Дорожка длиной %.2fм", obstacleSize);
    }
}
