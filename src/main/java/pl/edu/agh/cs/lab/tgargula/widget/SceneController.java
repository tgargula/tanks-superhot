package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import pl.edu.agh.cs.lab.tgargula.basics.Levels;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.engine.BulletEngine;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;
import pl.edu.agh.cs.lab.tgargula.engine.StatisticsEngine;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

public class SceneController {

    private static Levels level;
    private static int mapSize;
    private final SceneSwitchers sceneSwitchers = new SceneSwitchers();
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
    private WorldMapDrawer worldMapDrawer;

    public static void setLevel(int levelNo) {
        level = switch (levelNo) {
            case 1 -> Levels.EASY;
            case 2 -> Levels.MEDIUM;
            case 3 -> Levels.HARD;
            case 4 -> Levels.INSANE;
            default -> null;
        };
    }

    public static void setMapSize(int size) {
        mapSize = size;
    }

    @FXML
    private void initialize() {

        WorldMap worldMap = new WorldMap(mapSize);
        worldMapDrawer = new WorldMapDrawer(worldMap);

        engine = new Engine(
                worldMap,
                new StatisticsEngine(lifePane, score),
                new BulletEngine(commonBulletPane, bouncyBulletPane, fastBulletPane, strongBulletPane,
                        twoStepsPowerUpPane, immortalityPane),
                sceneSwitchers.gameOverSwitcher,
                level
        );
        System.out.println("Initialized");

        engine.add(new PlayerTank(Position.of(worldMap.getSize() / 2, worldMap.getSize() / 2), 3));

        engine.initialize();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }

    @FXML
    private void updateMap(KeyEvent event) {
        engine.update(event);
        worldMapPane.getChildren().clear();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }
}
