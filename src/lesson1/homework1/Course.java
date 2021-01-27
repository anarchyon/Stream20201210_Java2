package lesson1.homework1;

import java.util.ArrayList;

public class Course {
    public static final int OBSTACLES_COUNT_DEFAULT = 8;
    public static final int OBSTACLES_LIST_DEFAULT = 0;
    public static final int OBSTACLES_LIST_RANDOM = 1;
    public static final int OBSTACLES_LIST_MANUAL = 2;

    private ArrayList<Obstacle> obstacles;

    Course(int obstaclesListType){
        obstacles = new ArrayList<>();
        switch (obstaclesListType){
            case OBSTACLES_LIST_RANDOM:
                fillObstaclesListRandom();
                break;
            case OBSTACLES_LIST_MANUAL:
                fillObstaclesListManual();
                break;
            case OBSTACLES_LIST_DEFAULT:
            default:
                fillObstaclesListDefault();
                break;
        }
    }

    private void fillObstaclesListDefault() {
        for (int i = 0; i < OBSTACLES_COUNT_DEFAULT / 2; i++) {
            obstacles.add(new Treadmill());
            obstacles.add(new Wall());
        }
    }

    private void fillObstaclesListRandom() {

    }

    private void fillObstaclesListManual() {
        //TODO
        fillObstaclesListDefault();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
}
