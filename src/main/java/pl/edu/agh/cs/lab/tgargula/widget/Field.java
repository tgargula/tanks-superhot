package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Field extends Label {

    private final static Map<Position, Field> fields = new ConcurrentHashMap<>();


    private Field(Position position, ImageView imageView) {
        super("", imageView);
        this.getStyleClass().add("field");
        fields.put(position, this);
    }

    private Field(Position position) {
        this(position, new ImageView());
    }

    public static Field of(IWorldMap worldMap, Position position) {
        return fields.containsKey(position) ? fields.get(position) :
                worldMap.isOccupied(position) ? new Field(position, worldMap.getElementAt(position).getImageView()) :
                        new Field(position);
    }

}
