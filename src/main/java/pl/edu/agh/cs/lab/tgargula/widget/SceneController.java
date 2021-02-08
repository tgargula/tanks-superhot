package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.CommonBullet;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.engine.BulletEngine;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;
import pl.edu.agh.cs.lab.tgargula.engine.StatisticsEngine;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

import java.io.IOException;
import java.util.function.Consumer;

public class SceneController {

    private final WorldMap worldMap = new WorldMap(25);

    private final IDrawer worldMapDrawer = new WorldMapDrawer(worldMap);

    @FXML
    private HBox lifePane;

    @FXML
    private Text score;

    @FXML
    private Pane commonBulletPane;

    @FXML
    private Pane bouncyBulletPane;

    @FXML
    private Pane fastBulletPane;

    @FXML
    private Pane strongBulletPane;

    @FXML
    private Pane twoStepsPowerUpPane;

    @FXML
    private Pane immortalityPane;

    @FXML
    private Pane worldMapPane;

    private Engine engine;

    @FXML
    private void initialize() {

        Consumer<Integer> gameOver = points -> {
            Stage stage = (Stage) worldMapPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameOver.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(this.getClass().getResource("/style/game_over.css").toExternalForm());
                ((GameOverController) loader.getController()).getScoreText().setText("Score: " + points);
                stage.setScene(scene);
            } catch (IOException | NullPointerException e) {
                stage.close();
            }
        };

        engine = new Engine(
                worldMap,
                new StatisticsEngine(lifePane, score),
                new BulletEngine(commonBulletPane, bouncyBulletPane, fastBulletPane, strongBulletPane,
                        twoStepsPowerUpPane, immortalityPane),
                gameOver
        );
        System.out.println("Initialized");

        // Tests


        // worldMap
        engine.add(new CommonBullet(Position.of(15, 6), Direction.WEST));
        engine.add(new PlayerTank(Position.of(1, 1), 3));
        engine.add(new EnemyTank(Position.of(13, 13), 1));

        for (int i = 1; i < 10; i++) {
            engine.add(new Obstacle(Position.of(i, 10)));
            engine.add(new Obstacle(Position.of(10, i)));
        }
        engine.add(new Obstacle(Position.of(10, 10)));

        engine.initialize();
    }

    @FXML
    private void updateMap(KeyEvent event) {
        engine.update(event);
        worldMapPane.getChildren().clear();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }
}
