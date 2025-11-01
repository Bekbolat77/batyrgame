package batyrgame.core;

import batyrgame.observer.Observer;
import batyrgame.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Enemy implements Subject {
    private String name;
    private int health;
    private final List<Observer> observers = new ArrayList<>();

    public Enemy(String name) {
        this.name = name;
        this.health = 100;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            notifyObservers(name + " has been defeated!");
        } else {
            notifyObservers(name + " took " + damage + " damage. Remaining HP: " + health);
        }
    }

    @Override
    public void registerObserver(Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers(String event) {
        for (Observer o : observers) o.update(event);
    }
}