package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

import java.util.Random;

public abstract class AbstractMovable extends AbstractElement implements IMovable {

    protected Direction direction;

    protected AbstractMovable(Position position, Direction direction, ImageView imageView) {
        super(position, imageView);
        this.direction = direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public ImageView getImageView() {
        ImageView imageView = super.getImageView();
        imageView.setRotate(this.direction.getAngle());
        return imageView;
    }
}
