package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

public class FastBullet extends AbstractBullet {

    public FastBullet(Position position, Direction direction) {
        super(position, direction, new ImageView(new Image("/images/fast_bullet.png")), 1);
    }

    @Override
    public void move() {
        super.move();
        super.move();
    }

    @Override
    public Position nextPosition() {
        return super.nextPosition().add(direction.toUnitVector());
    }
}
