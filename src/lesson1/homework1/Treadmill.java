package lesson1.homework1;

public class Treadmill implements WithRandom{
    private final double distance;

    Treadmill(double averageDistance, double spread) {
        distance = getRandom(averageDistance, spread);
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return String.format("Дорожка длиной %.2fм", distance);
    }
}
