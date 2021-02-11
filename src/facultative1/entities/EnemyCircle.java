package facultative1.entities;

import java.util.Random;

public class EnemyCircle extends Circle {

    private EnemyCircle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int width = random.nextInt();
        int height = random.nextInt();
        int x = random.nextInt();
        int y = random.nextInt();

        return new EnemyCircle(x, y, width, height);
    }

}
