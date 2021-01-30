package lesson1.homework1.participants;

import lesson1.homework1.common.DefaultParameters;
import lesson1.homework1.common.ObstaclesOvercoming;
import lesson1.homework1.common.WithRandom;
import lesson1.homework1.obstacles.Obstacle;

public class Cat implements ObstaclesOvercoming, WithRandom {
    private static final String TYPE = "Кошка";

    private String name;
    private double jumpHeight;
    private double runDistance;
    private boolean isTrialSuccess;
    private boolean isKnownObstacle;

    private static int counter;

    public Cat() {
        jumpHeight = getRandom(DefaultParameters.CAT_AVERAGE_JUMP_HEIGHT, DefaultParameters.CAT_JUMP_SPREAD);
        runDistance = getRandom(DefaultParameters.CAT_AVERAGE_RUN_DISTANCE, DefaultParameters.CAT_DISTANCE_SPREAD);
        name = TYPE + ++counter;
    }

    public Cat(double averageHeight, double spreadJump, double averageDistance, double spreadDistance) {
        jumpHeight = getRandom(averageHeight, spreadJump);
        runDistance = getRandom(averageDistance, spreadDistance);
        name = TYPE + ++counter;
    }

    @Override
    public void getStarted() {
        isTrialSuccess = true;
        isKnownObstacle = true;
    }

    @Override
    public boolean isTrialSuccess() {
        return isTrialSuccess;
    }

    @Override
    public boolean isKnownObstacle() { return isKnownObstacle; }

    @Override
    public void analyzeObstacle(Obstacle obstacle) {
        switch (obstacle.getType()) {
            case DefaultParameters.OBSTACLE_TYPE_CAN_BE_JUMPED_OVER:
                isTrialSuccess = jumpOver(jumpHeight, obstacle);
                break;
            case DefaultParameters.OBSTACLE_TYPE_CAN_BE_RUN:
                isTrialSuccess = run(runDistance, obstacle);
                break;
            default:
                isTrialSuccess = false;
                isKnownObstacle = false;
                break;
        }
    }

    @Override
    public String toString() {
        return TYPE + " " + name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
