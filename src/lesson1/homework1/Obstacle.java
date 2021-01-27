package lesson1.homework1;

public abstract class Obstacle implements WithRandom{
    int type;
    double obstacleSize;

    public Obstacle(){

    }

    public Obstacle (double averageSize, double spread) {
        obstacleSize = getRandom(averageSize, spread);
    }

    public int getType() {
        return type;
    }

    public double getObstacleSize(){
        return obstacleSize;
    }
}
