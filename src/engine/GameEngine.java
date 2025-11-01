package batyrgame.engine;

import batyrgame.core.Batyr;
import batyrgame.core.Enemy;
import batyrgame.factory.BatyrFactory;
import batyrgame.strategy.AttackStrategy;
import batyrgame.strategy.MoveStrategy;
import batyrgame.ui.GameUI;

public class GameEngine {
    private static GameEngine instance;
    private GameUI ui;
    private Batyr batyr;
    private Enemy enemy;

    private GameEngine() {}

    public static GameEngine getInstance() {
        if (instance == null) instance = new GameEngine();
        return instance;
    }

    public void setUI(GameUI ui) {
        this.ui = ui;
    }

    public void initGame() {
        batyr = BatyrFactory.create("Batyr");
        enemy = new Enemy("Enemy");

        batyr.registerObserver(ui.getObserver());
        enemy.registerObserver(ui.getObserver());

        ui.bindCharacters(batyr, enemy);
    }

    public void move(MoveStrategy strategy) {
        batyr.setMoveStrategy(strategy);
        batyr.move();
    }

    public void attack(AttackStrategy strategy) {
        batyr.setAttackStrategy(strategy);
        int damage = strategy.attack();
        enemy.takeDamage(damage);
        batyr.attack();
        ui.updateHealthBars(batyr.getHealth(), enemy.getHealth());
    }
}