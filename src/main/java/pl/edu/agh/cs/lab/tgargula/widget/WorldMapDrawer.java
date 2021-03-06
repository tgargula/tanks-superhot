package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

public class WorldMapDrawer implements IDrawer {

    private final WorldMap map;

    public WorldMapDrawer(WorldMap map) {
        this.map = map;
    }

    @Override
    public Pane draw() {
        VBox pane = new VBox();
        pane.getStyleClass().add("world-map");
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            Pane row = new HBox();
            row.getStyleClass().add("world-map");
            for (int j = 0; j < size; j++)
                row.getChildren().add(Field.of(map, Position.of(j, i)));
            pane.getChildren().add(row);
        }
        return pane;
    }
}
