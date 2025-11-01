package batyrgame.ui;

import batyrgame.core.Batyr;
import batyrgame.core.Enemy;
import batyrgame.engine.GameEngine;
import batyrgame.observer.GameObserver;
import batyrgame.strategy.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameUI {
    private Canvas canvas;
    private GraphicsContext gc;
    private TextArea logArea;
    private Batyr batyr;
    private Enemy enemy;
    private GameObserver observer;
    private ProgressBar batyrHP;
    private ProgressBar enemyHP;

    public void start(Stage stage, GameEngine engine) {
        canvas = new Canvas(500, 300);
        gc = canvas.getGraphicsContext2D();
        logArea = new TextArea();
        logArea.setEditable(false);

        observer = new GameObserver(this);

        batyrHP = new ProgressBar(1.0);
        enemyHP = new ProgressBar(1.0);
        batyrHP.setPrefWidth(200);
        enemyHP.setPrefWidth(200);


        Button leftBtn = new Button("← Left");
        Button rightBtn = new Button("Right →");

        leftBtn.setOnAction(e -> engine.move(new MoveLeft()));
        rightBtn.setOnAction(e -> engine.move(new MoveRight()));


        Button meleeBtn = new Button("Melee");
        Button rangedBtn = new Button("Ranged");
        Button magicBtn = new Button("Magic");

        meleeBtn.setOnAction(e -> engine.attack(new MeleeAttack()));
        rangedBtn.setOnAction(e -> engine.attack(new RangedAttack()));
        magicBtn.setOnAction(e -> engine.attack(new MagicAttack()));

        HBox controls = new HBox(10, leftBtn, rightBtn, meleeBtn, rangedBtn, magicBtn);
        controls.setAlignment(Pos.CENTER);

        VBox hpBars = new VBox(5,
                new HBox(10, batyrHP, enemyHP)
        );
        hpBars.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setTop(hpBars);
        root.setBottom(controls);
        root.setRight(logArea);

        Scene scene = new Scene(root, 750, 420);
        stage.setScene(scene);
        stage.setTitle("BatyrGame - Hero Battle Simulation");
        stage.show();

        engine.initGame();
    }

    public void bindCharacters(Batyr batyr, Enemy enemy) {
        this.batyr = batyr;
        this.enemy = enemy;
        draw();
    }

    public void draw() {
        Platform.runLater(() -> {
            gc.clearRect(0, 0, 500, 300);
            gc.fillText("🧍 " + batyr.getName(), batyr.getX(), 150);
            gc.fillText("🧟 " + enemy.getName(), 400, 150);
        });
    }

    public void updateHealthBars(int batyrHp, int enemyHp) {
        Platform.runLater(() -> {
            this.batyrHP.setProgress(batyrHp / 100.0);
            this.enemyHP.setProgress(enemyHp / 100.0);
        });
    }

    public void log(String message) {
        Platform.runLater(() -> {
            logArea.appendText(message + "\n");
            draw();
        });
    }

    public GameObserver getObserver() {
        return observer;
    }
}