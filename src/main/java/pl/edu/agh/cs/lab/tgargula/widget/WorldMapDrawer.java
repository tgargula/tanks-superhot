package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class WorldMapDrawer {

    private final IWorldMap map;

    public WorldMapDrawer(IWorldMap map) {
        this.map = map;
    }

    public Pane draw() {
        Pane pane = new VBox();
        int size = map.getSize();
        for (int i = 0; i < size; i++) {
            Pane row = new HBox();
            for (int j = 0; j < size; j++) {
                System.out.println("null");
            }
        }
        return null;
    }
}
