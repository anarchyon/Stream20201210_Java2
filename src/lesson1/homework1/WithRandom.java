package lesson1.homework1;

import java.util.Random;

public interface WithRandom {
    default double getRandom(double averageValue, double spread) {
        Random random = new Random();
        double dVal = (random.nextDouble() * 2 - 1) * averageValue * spread;
        return averageValue + dVal;
    }
}
