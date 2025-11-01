package batyrgame.strategy;

public class MoveRight implements MoveStrategy {
    @Override
    public double move(double currentX) {
        return currentX + 10;
    }

    @Override
    public String getName() {
        return "Right";
    }
}