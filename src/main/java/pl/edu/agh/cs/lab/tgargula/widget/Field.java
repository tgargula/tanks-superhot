package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.scene.control.Label;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Field extends Label {

    private final static Map<Position, Field> fields = new ConcurrentHashMap<>();

    private Field(IWorldMap worldMap, Position position) {
        this.getStyleClass().add("field");
        if (worldMap.isOccupied(position))
            this.getChildren().add(worldMap.getElementAt(position).getImageView());
        fields.put(position, this);
    }

    public static Field of(IWorldMap worldMap, Position position) {
        return fields.containsKey(position) ? fields.get(position) : new Field(worldMap, position);
    }

}
