package batyrgame.core;

import batyrgame.observer.Observer;
import batyrgame.observer.Subject;
import batyrgame.strategy.AttackStrategy;
import batyrgame.strategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Batyr implements Subject {
    private String name;
    private double x;
    private int health;
    private MoveStrategy moveStrategy;
    private AttackStrategy attackStrategy;
    private final List<Observer> observers = new ArrayList<>();

    public Batyr(String name, double startX) {
        this.name = name;
        this.x = startX;
        this.health = 100;
    }

    public String getName() { return name; }
    public double getX() { return x; }
    public int getHealth() { return health; }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
        notifyObservers("Attack strategy changed to " + attackStrategy.getName());
    }

    public void move() {
        x = moveStrategy.move(x);
        notifyObservers(name + " moved " + moveStrategy.getName().toLowerCase() + ".");
    }

    public void attack() {
        if (attackStrategy == null) {
            notifyObservers("No attack strategy selected!");
            return;
        }
        int damage = attackStrategy.attack();
        notifyObservers(name + " attacked using " + attackStrategy.getName() + " dealing " + damage + " dmg!");
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}