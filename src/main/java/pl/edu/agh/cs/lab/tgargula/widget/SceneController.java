package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.BouncyBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.CommonBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.FastBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.StrongBullet;
import pl.edu.agh.cs.lab.tgargula.elements.powerups.ImmortalityPowerUp;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class SceneController {

    private final IWorldMap worldMap = new WorldMap(25);

    private final IDrawer worldMapDrawer = new WorldMapDrawer(worldMap);

    @FXML
    private Pane worldMapPane;

    @FXML
    private void initialize() {
        System.out.println("Initialized");
        worldMap.observe(new CommonBullet(Position.of(0, 0), Direction.EAST));
        worldMap.observe(new FastBullet(Position.of(1, 0), Direction.EAST));
        worldMap.observe(new CommonBullet(Position.of(15, 6), Direction.NORTH));
        worldMap.observe(new StrongBullet(Position.of(2, 0), Direction.NORTHEAST));
        worldMap.observe(new BouncyBullet(Position.of(3, 0), Direction.WEST));
        worldMap.observe(new PlayerTank(Position.of(0, 1), 10));
        worldMap.observe(new EnemyTank(Position.of(1, 1), 1));
        worldMap.observe(new ImmortalityPowerUp(Position.of(2, 1)));
        worldMap.observe(new Obstacle(Position.of(3,1)));
    }

    @FXML
    private void updateMap(KeyEvent event) {
        worldMapPane.getChildren().clear();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }
}
