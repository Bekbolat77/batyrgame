package batyrgame;

import batyrgame.engine.GameEngine;
import batyrgame.ui.GameUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GameEngine engine = GameEngine.getInstance();
        GameUI ui = new GameUI();
        engine.setUI(ui);
        ui.start(stage, engine);
    }

    public static void main(String[] args) {
        launch(args);
    }
}