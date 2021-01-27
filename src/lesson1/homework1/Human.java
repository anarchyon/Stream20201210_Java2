package lesson1.homework1;

public class Human implements ObstaclesOvercoming, WithRandom {
    private static final String TYPE = "Человек";

    private String name;
    private double jumpHeight;
    private double runDistance;
    private boolean isTrialSuccess;

    private static int counter;

    Human() {
        jumpHeight = getRandom(DefaultParameters.HUMAN_AVERAGE_JUMP_HEIGHT, DefaultParameters.HUMAN_JUMP_SPREAD);
        runDistance = getRandom(DefaultParameters.HUMAN_AVERAGE_RUN_DISTANCE, DefaultParameters.HUMAN_DISTANCE_SPREAD);
        name = TYPE + ++counter;
    }

    Human(double averageHeight, double spreadJump, double averageDistance, double spreadDistance) {
        jumpHeight = getRandom(averageHeight, spreadJump);
        runDistance = getRandom(averageDistance, spreadDistance);
        name = TYPE + ++counter;
    }

    @Override
    public void getStarted() {
        isTrialSuccess = true;
    }

    @Override
    public boolean isTrialSuccess() {
        return isTrialSuccess;
    }

    @Override
    public void jumpOver(Obstacle obstacle) {
        if (obstacle.getType() == DefaultParameters.OBSTACLE_TYPE_CAN_BE_JUMPED_OVER) {
            double obstacleHeight = obstacle.getObstacleSize();
            if (jumpHeight < obstacleHeight) isTrialSuccess = false;
        }
    }

    @Override
    public void run(Obstacle obstacle) {
        if (obstacle.getType() == DefaultParameters.OBSTACLE_TYPE_CAN_BE_RUN) {
            double distance = obstacle.getObstacleSize();
            if (runDistance < distance) isTrialSuccess = false;
        }
    }

    @Override
    public String toString() {
        return (TYPE + " " + name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
