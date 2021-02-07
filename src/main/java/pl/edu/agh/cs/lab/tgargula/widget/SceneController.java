package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    private Pane worldMapPane;

    private Engine engine;

    @FXML
    private void initialize() {
        engine = new Engine(
                worldMap,
                new StatisticsEngine(lifePane, score),
                new BulletEngine(commonBulletPane, bouncyBulletPane, fastBulletPane, strongBulletPane)
        );
        System.out.println("Initialized");

        // Tests


        // worldMap
////        engine.add(new CommonBullet(Position.of(0, 0), Direction.EAST));
////        engine.add(new FastBullet(Position.of(1, 0), Direction.EAST));
        engine.add(new CommonBullet(Position.of(15, 6), Direction.WEST));
////        engine.add(new StrongBullet(Position.of(2, 0), Direction.NORTHEAST));
////        engine.add(new BouncyBullet(Position.of(3, 0), Direction.WEST));
        engine.add(new PlayerTank(Position.of(1, 1), 3));
        engine.add(new EnemyTank(Position.of(13, 13), 1));

        for (int i = 1; i < 10; i++) {
            engine.add(new Obstacle(Position.of(i, 10)));
            engine.add(new Obstacle(Position.of(10, i)));
        }
        engine.add(new Obstacle(Position.of(10, 10)));
////        engine.add(new ImmortalityPowerUp(Position.of(2, 1)));
////        engine.add(new Obstacle(Position.of(3,1)));
//        engine.add(new Obstacle(Position.of(0,0)));

        engine.initialize();
    }

    @FXML
    private void updateMap(KeyEvent event) {
        engine.update(event);
        worldMapPane.getChildren().clear();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }
}
