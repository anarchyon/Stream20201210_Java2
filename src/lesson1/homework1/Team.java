package lesson1.homework1;

import java.util.ArrayList;
import java.util.Random;

public class Team {
    public static final int TEAM_DEFAULT = 0;
    public static final int TEAM_RANDOM = 1;
    public static final int TEAM_MANUAL = 2;
    public static final int TEAM_COUNT_DEFAULT = 4;

    private boolean wasTeamInCompetition;

    private String teamName;
    private ArrayList<ObstaclesOvercoming> listOfMembers;

    Team(String teamName) {
        this.teamName = teamName;
        listOfMembers = new ArrayList<>();
    }

    Team(String teamName, int typeOfTeam) {
        this.teamName = teamName;
        listOfMembers = new ArrayList<>();
        switch (typeOfTeam) {
            case TEAM_RANDOM:
                fillTeamRandom();
                break;
            case TEAM_MANUAL:
                fillTeamManual();
                break;
            case TEAM_DEFAULT:
            default:
                fillTeamDefault();
                break;
        }
    }

    private void fillTeamDefault() {
        listOfMembers.add(new Human());
        listOfMembers.add(new Robot());
        listOfMembers.add(new Robot());
        listOfMembers.add(new Cat());
    }

    private void fillTeamRandom() {
        Random random = new Random();
        for (int i = 0; i < TEAM_COUNT_DEFAULT; i++) {
            int p = random.nextInt(DefaultParameters.CURRENT_MEMBER_TYPES_COUNT);
            switch (p) {
                case 1:
                    listOfMembers.add(new Cat());
                    break;
                case 2:
                    listOfMembers.add(new Robot());
                    break;
                case 0:
                default:
                    listOfMembers.add(new Human());
                    break;
            }
        }
    }

    private void fillTeamManual() {
        //TODO
        fillTeamDefault();
    }

    public void addMember(ObstaclesOvercoming member) {
        listOfMembers.add(member);
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<ObstaclesOvercoming> getListOfMembers() {
        return listOfMembers;
    }

    public String teamMembersInfo() {
        return String.format("Участники команды %s: %s", teamName, listOfMembers.toString());
    }

    public String competitionResults() {
        if (wasTeamInCompetition) {
            ArrayList<ObstaclesOvercoming> passed = new ArrayList<>();
            for (ObstaclesOvercoming member : listOfMembers){
                if (member.isTrialSuccess()){
                    passed.add(member);
                }
            }
            if (passed.size() == 0) {
                return String.format("Ни один из участников команды %s не прошел испытания", teamName);
            } else {
                return String.format("Список участников команды %s, прошедших испытания: %s", teamName, passed.toString());
            }
        }
        return String.format("Команда %s не участвовала в испытаниях", teamName);
    }

    public void setWasTeamInCompetition(boolean wasTeamInCompetition) {
        this.wasTeamInCompetition = wasTeamInCompetition;
    }
}
