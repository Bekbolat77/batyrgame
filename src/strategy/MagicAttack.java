package batyrgame.strategy;

import java.util.Random;
public class MagicAttack implements AttackStrategy {
      @Override
     public int attack() {
        return new Random().nextInt(20) + 20; // 20–39
    }

    @Override
    public String getName() {
        return "Magic Attack";
    }
}