package lesson1.homework1;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;

public class Main {

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

        System.out.println(randomTeam.teamMembersInfo());
        System.out.println(randomTeam.competitionInfo());
        System.out.println(defaultTeam.teamMembersInfo());
        System.out.println(randomTeam.competitionInfo());
/*
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        for (int i = 0; i < STAGE_COUNT / 2; i++) {
            obstacles.add(new Wall());
            obstacles.add(new Treadmill());
        }

        for (ObstaclesOvercoming participant : participants) {
            System.out.println("Участник " + participant.toString());
            participant.getStarted();
            for (Obstacle obstacle : obstacles) {
                participant.jumpOver(obstacle);
                participant.run(obstacle);
                if (!participant.isTrialSuccess()) {
                    System.out.printf("%s не смог преодолеть препятствие №%s: %s\n", participant.toString(), obstacles.indexOf(obstacle) + 1, obstacle.toString());
                    break;
                }
                System.out.printf("Препятствие №%s преодолено\n", obstacles.indexOf(obstacle) + 1);
            }
            if (participant.isTrialSuccess()) {
                System.out.printf("%s преодолел все препятствия!\n", participant.toString());
            }

        }
*/
    }
}
