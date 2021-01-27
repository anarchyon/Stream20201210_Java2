package lesson1.homework1;

public class Main {

    public static void main(String[] args) {
        Team randomTeam = new Team("Ракета", Team.TEAM_RANDOM);
        Team defaultTeam = new Team("Метеор", Team.TEAM_DEFAULT);
        Course course1 = new Course(Course.OBSTACLES_LIST_DEFAULT);
        Course course2 = new Course(Course.OBSTACLES_LIST_RANDOM);

        System.out.println(randomTeam.teamMembersInfo());
        course1.startCompetition(randomTeam);
        System.out.println(randomTeam.competitionResults());

        System.out.println("------------------");

        System.out.println(defaultTeam.teamMembersInfo());
        course2.startCompetition(defaultTeam);
        System.out.println(defaultTeam.competitionResults());
    }
}
