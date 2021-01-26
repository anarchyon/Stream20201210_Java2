package lesson1.homework1;

public class Cat implements ObstaclesOvercoming, WithRandom {
    private static final String TYPE = "Кошка";

    private String name;
    private double jumpHeight;
    private double runDistance;
    private boolean isTrialSuccess;

    private static int counter;

    Cat() {
        jumpHeight = getRandom(Main.CAT_AVERAGE_JUMP_HEIGHT, Main.CAT_JUMP_SPREAD);
        runDistance = getRandom(Main.CAT_AVERAGE_RUN_DISTANCE, Main.CAT_DISTANCE_SPREAD);
        name = TYPE + ++counter;
    }

    Cat(double averageHeight, double spreadJump, double averageDistance, double spreadDistance) {
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
    public void jumpOver(Object object) {
        if (object instanceof Obstacle) {
            double obstacleHeight = ((Obstacle) object).getHeight();
            if (jumpHeight < obstacleHeight) isTrialSuccess = false;
        }
    }

    @Override
    public void run(Object object) {
        if (object instanceof Treadmill) {
            double distance = ((Treadmill) object).getDistance();
            if (runDistance < distance) isTrialSuccess = false;
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
