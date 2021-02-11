package facultative1;

import facultative1.entities.EnemyCircle;
import facultative1.entities.HeroCircle;

import java.util.ArrayList;


public class Manager {

    private HeroCircle hero;
    private ArrayList<EnemyCircle> enemies;
    GamePanel gameField;

    public Manager (GamePanel gamePanel) {
        this.gameField = gamePanel;
        init();
    }

    private void init() {
        hero = new HeroCircle(MainWindow.WIDTH / 2, MainWindow.HEIGHT / 2);
        enemies = new ArrayList<>();
        for (int i = 0; i < CircleParams.NUMBER_OF_ENEMY; i++) {
            enemies.add(EnemyCircle.getRandomCircle());
        }
    }

    public HeroCircle getHero() {
        return hero;
    }

    public void go(int x, int y) {
        hero.move(x, y);
    }
}
