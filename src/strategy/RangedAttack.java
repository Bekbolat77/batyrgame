package batyrgame.strategy;

import java.util.Random;

public class RangedAttack implements AttackStrategy {
    @Override
    public int attack() {
        return new Random().nextInt(15) + 10; // 10–24
    }

    @Override
    public String getName() {
        return "Ranged Attack";
    }
}