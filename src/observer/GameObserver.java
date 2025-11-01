package batyrgame.observer;

import batyrgame.ui.GameUI;

public class GameObserver implements Observer {
    private final GameUI ui;

    public GameObserver(GameUI ui) {
        this.ui = ui;
    }

    @Override
    public void update(String event) {
        ui.log(event);
    }
}