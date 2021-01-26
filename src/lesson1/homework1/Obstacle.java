package lesson1.homework1;

public class Obstacle implements WithRandom {
    private final double height;

    Obstacle(double averageHeight, double spread) {
        height = getRandom(averageHeight, spread);
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString () {
        return String.format("Стена высотой %.2fм", height);
    }
}
