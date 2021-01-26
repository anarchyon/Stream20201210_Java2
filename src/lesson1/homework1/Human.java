package lesson1.homework1;

public class Human implements AbleToJumpAndRun, WithRandom {
    private String name;
    private double jumpHeight;
    private double runDistance;
    private boolean isTrialSuccess;

    private static int counter;

    Human(double averageHeight, double spreadJump, double averageDistance, double spreadDistance) {
        jumpHeight = getRandom(averageHeight, spreadJump);
        runDistance = getRandom(averageDistance, spreadDistance);
        name = "Human" + ++counter;
        isTrialSuccess = true;
    }

    public void getStarted() {
        isTrialSuccess = true;
    }

    @Override
    public boolean isTrialSuccess() {
        return isTrialSuccess;
    }

    @Override
    public void jumpOver(Object object) {
        if (object instanceof Obstacle) {
            double obstacleHeight = ((Obstacle)object).getHeight();
            if (jumpHeight < obstacleHeight) isTrialSuccess = false;
        }
    }

    @Override
    public void run(Object object) {
        if (object instanceof Treadmill) {
            double distance = ((Treadmill)object).getDistance();
            if (runDistance < distance) isTrialSuccess = false;
        }
    }

    @Override
    public String toString(){
        return String.format("Человек %s", name);
    }
}
