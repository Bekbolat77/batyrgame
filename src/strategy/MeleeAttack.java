package batyrgame.strategy;

import java.util.Random;

public class MeleeAttack implements AttackStrategy {
    @Override
    public int attack() {
        return new Random().nextInt(10) + 15; // 15–24
    }

    @Override
    public String getName() {
        return "Melee Attack";
    }
}