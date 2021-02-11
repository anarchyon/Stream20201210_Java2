package facultative1.entities;

import facultative1.CircleParams;
import facultative1.MainWindow;

import java.util.Random;

public class EnemyCircle extends Circle {

    private EnemyCircle(int x, int y, int diameter) {
        super(x, y, diameter);
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int diameter = CircleParams.ENEMY_MIN_DIAMETER + random.nextInt(
                CircleParams.ENEMY_MAX_DIAMETER - CircleParams.ENEMY_MIN_DIAMETER);
        int x = random.nextInt(MainWindow.WIDTH - diameter);
        int y = random.nextInt(MainWindow.HEIGHT - diameter);

        return new EnemyCircle(x, y, diameter);
    }

}
