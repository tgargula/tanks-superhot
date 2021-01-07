package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

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
    public void move() {
        System.out.println(this);
        this.position = position.add(direction.toUnitVector());
        this.update();
    }

    @Override
    public ImageView getImageView() {
        ImageView imageView = super.getImageView();
        imageView.setRotate(this.direction.getAngle());
        return imageView;
    }
}
