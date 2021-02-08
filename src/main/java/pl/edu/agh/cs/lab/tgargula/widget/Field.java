package pl.edu.agh.cs.lab.tgargula.widget;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

public class Field extends Pane {

    private Field(ImageView imageView) {
        super(imageView);
        this.getStyleClass().add("field");
    }

    private Field() {
        this(new ImageView());
    }

    public static Field of(WorldMap worldMap, Position position) {
        return worldMap.isOccupied(position) ?
                new Field(worldMap.getElementAt(position).getImageView()) : new Field();
    }

}
