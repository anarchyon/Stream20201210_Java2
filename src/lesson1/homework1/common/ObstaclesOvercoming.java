package lesson1.homework1.common;

import lesson1.homework1.obstacles.Obstacle;

public interface ObstaclesOvercoming {

    default boolean jumpOver(double participantJumpHeight, Obstacle obstacle) {
        return participantJumpHeight >= obstacle.getObstacleSize();
    }

    default boolean run(double participantRunDistance, Obstacle obstacle) {
        return participantRunDistance >= obstacle.getObstacleSize();
    }

    void analyzeObstacle(Obstacle obstacle);

    boolean isTrialSuccess();

    boolean isKnownObstacle();

    void getStarted();

    default String getReport(boolean isKnownObstacle, boolean isTrialSuccess) {
        if (!isKnownObstacle) {
            return String.format("Встречено неизвестное препятствие! %s не смог преодолеть препятствие", toString());
        } else if (!isTrialSuccess) {
            return String.format("%s не смог преодолеть препятствие", toString());
        } else {
            return String.format("%s преодолел препятствие", toString());
        }

    }
}
