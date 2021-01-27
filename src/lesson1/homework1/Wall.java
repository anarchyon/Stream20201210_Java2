package lesson1.homework1;

public class Wall extends Obstacle {

    Wall() {
        super(DefaultParameters.OBSTACLE_AVERAGE_HEIGHT, DefaultParameters.OBSTACLE_HEIGHT_SPREAD);
        type = DefaultParameters.OBSTACLE_TYPE_CAN_BE_JUMPED_OVER;
    }

    @Override
    public String toString () {
        return String.format("Стена высотой %.2fм", obstacleSize);
    }
}
