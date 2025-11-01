package batyrgame.strategy;

public class MoveLeft implements MoveStrategy {
    @Override
    public double move(double currentX) {
        return currentX - 10;
    }

    @Override
    public String getName() {
        return "Left";
    }
}