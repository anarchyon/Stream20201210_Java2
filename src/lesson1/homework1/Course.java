package lesson1.homework1;

import java.util.ArrayList;
import java.util.Random;

public class Course {
    public static final int OBSTACLES_COUNT_DEFAULT = 8;
    public static final int OBSTACLES_LIST_DEFAULT = 0;
    public static final int OBSTACLES_LIST_RANDOM = 1;
    public static final int OBSTACLES_LIST_MANUAL = 2;

    private ArrayList<Obstacle> obstacles;

    Course(int obstaclesListType) {
        obstacles = new ArrayList<>();
        switch (obstaclesListType) {
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
        Random random = new Random();
        for (int i = 0; i < OBSTACLES_COUNT_DEFAULT; i++) {
            int o = random.nextInt(DefaultParameters.CURRENT_OBSTACLE_TYPES_COUNT);
            switch (o) {
                case 1:
                    obstacles.add(new Treadmill());
                    break;
                case 0:
                default:
                    obstacles.add(new Wall());
                    break;

            }
        }
    }

    private void fillObstaclesListManual() {
        //TODO
        fillObstaclesListDefault();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void startCompetition(Team team) {
        for (ObstaclesOvercoming participant : team.getListOfMembers()) {
            participant.getStarted();
            for (Obstacle obstacle : obstacles) {
                participant.analyzeObstacle(obstacle);
                System.out.println(participant.getReport(participant.isKnownObstacle(), participant.isTrialSuccess()));
                if (!participant.isTrialSuccess()) {
                    break;
                }
            }
        }
        team.setWasTeamInCompetition(true);
    }
}
