package facultative1.entities;

import facultative1.CircleParams;
import facultative1.MainWindow;

import java.util.Random;

public class EnemyCircle extends Circle {

    private EnemyCircle(int x, int y, int diameter, double dX, double dY, double speed ) {
        super(x, y, diameter);
        this.dX = dX;
        this.dY = dY;
        this.speed = speed;
    }

    public static EnemyCircle getRandomCircle(int fieldWidth, int fieldHeight) {
        Random random = new Random();
        int diameter = CircleParams.ENEMY_MIN_DIAMETER + random.nextInt(
                CircleParams.ENEMY_MAX_DIAMETER - CircleParams.ENEMY_MIN_DIAMETER);
        int x = random.nextInt(fieldWidth - diameter);
        int y = random.nextInt(fieldHeight - diameter);
        double dX = random.nextDouble() * 2 - 1;
        double dY = random.nextDouble() * 2 - 1;
        double speed = random.nextDouble() * 2 + 1;

        return new EnemyCircle(x, y, diameter, dX, dY, speed);
    }

}
