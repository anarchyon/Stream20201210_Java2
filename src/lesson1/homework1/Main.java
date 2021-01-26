package lesson1.homework1;

import java.util.ArrayList;

public class Main {
    public static final double HUMAN_AVERAGE_JUMP_HEIGHT = 0.7;
    public static final double HUMAN_JUMP_SPREAD = 0.6;

    public static final double CAT_AVERAGE_JUMP_HEIGHT = 1.2;
    public static final double CAT_JUMP_SPREAD = 0.5;

    public static final double ROBOT_AVERAGE_JUMP_HEIGHT = 1;
    public static final double ROBOT_JUMP_SPREAD = 0.3;

    public static final double OBSTACLE_AVERAGE_HEIGHT = 0.6;
    public static final double OBSTACLE_HEIGHT_SPREAD = 0.2;

    public static final double HUMAN_AVERAGE_RUN_DISTANCE = 1600;
    public static final double HUMAN_DISTANCE_SPREAD = 0.3;

    public static final double CAT_AVERAGE_RUN_DISTANCE = 500;
    public static final double CAT_DISTANCE_SPREAD = 0.4;

    public static final double ROBOT_AVERAGE_RUN_DISTANCE = 2000;
    public static final double ROBOT_DISTANCE_SPREAD = 0.4;

    public static final double AVERAGE_DISTANCE = 700;
    public static final double DISTANCE_SPREAD = 0.5;

    public static final int STAGE_COUNT = 8;

    public static void main(String[] args) {
        Team randomTeam = new Team("Ракета", Team.TEAM_RANDOM);
        Team defaultTeam = new Team("Метеор", Team.TEAM_DEFAULT);
        Human human = new Human();
        Cat cat = new Cat();
        Robot robot = new Robot();
        ArrayList<ObstaclesOvercoming> participants = new ArrayList<>();
        participants.add(human);
        participants.add(cat);
        participants.add(robot);

        ArrayList<Object> stages = new ArrayList<>();
        for (int i = 0; i < STAGE_COUNT / 2; i++) {
            stages.add(new Obstacle(OBSTACLE_AVERAGE_HEIGHT, OBSTACLE_HEIGHT_SPREAD));
            stages.add(new Treadmill(AVERAGE_DISTANCE, DISTANCE_SPREAD));
        }

        for (ObstaclesOvercoming participant : participants) {
            System.out.println("Участник " + participant.toString());
            participant.getStarted();
            for (Object object : stages) {
                participant.jumpOver(object);
                participant.run(object);
                if (!participant.isTrialSuccess()) {
                    System.out.printf("%s не смог преодолеть препятствие №%s: %s\n", participant.toString(), stages.indexOf(object) + 1, object.toString());
                    break;
                }
                System.out.printf("Препятствие №%s преодолено\n", stages.indexOf(object) + 1);
            }
            if (participant.isTrialSuccess()) {
                System.out.printf("%s преодолел все препятствия!\n", participant.toString());
            }

        }
    }
}
