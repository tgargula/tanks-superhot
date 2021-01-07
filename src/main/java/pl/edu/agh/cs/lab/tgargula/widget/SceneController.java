package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Heart;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.BouncyBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.CommonBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.FastBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.StrongBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.elements.powerups.ImmortalityPowerUp;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;
import pl.edu.agh.cs.lab.tgargula.engine.IEngine;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class SceneController {

    private final IWorldMap worldMap = new WorldMap(25);

    private final IDrawer worldMapDrawer = new WorldMapDrawer(worldMap);
    
    private final IEngine engine = new Engine(worldMap);
    
    @FXML
    private HBox lifePane;

    @FXML
    private HBox bulletsPane;

    @FXML
    private Pane worldMapPane;

    @FXML
    private void initialize() {
        System.out.println("Initialized");

        // Tests

        // worldMap
//        engine.add(new CommonBullet(Position.of(0, 0), Direction.EAST));
//        engine.add(new FastBullet(Position.of(1, 0), Direction.EAST));
        engine.add(new CommonBullet(Position.of(15, 6), Direction.NORTH));
//        engine.add(new StrongBullet(Position.of(2, 0), Direction.NORTHEAST));
//        engine.add(new BouncyBullet(Position.of(3, 0), Direction.WEST));
        engine.add(new PlayerTank(Position.of(0, 1), 10));
        engine.add(new EnemyTank(Position.of(13, 13), 1));
//        engine.add(new ImmortalityPowerUp(Position.of(2, 1)));
//        engine.add(new Obstacle(Position.of(3,1)));

        // lifePane
        lifePane.getChildren().add(new Heart().getImageView());
        lifePane.getChildren().add(new Heart().getImageView());
        lifePane.getChildren().add(new Heart().getImageView());

    }

    @FXML
    private void updateMap(KeyEvent event) {
        engine.update(event);
        worldMapPane.getChildren().clear();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }
}
