package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.CommonBullet;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class SceneController {

    private final IWorldMap worldMap = new WorldMap(5);

    private final IDrawer worldMapDrawer = new WorldMapDrawer(worldMap);

    @FXML
    private Pane worldMapPane;

    @FXML
    private void initialize() {
        System.out.println("Initialized");
        worldMap.observe(new CommonBullet(Position.of(1,1), Direction.EAST));
    }

    @FXML
    private void updateMap(KeyEvent event) {
        worldMapPane.getChildren().clear();
        worldMapPane.getChildren().add(worldMapDrawer.draw());
    }
}
