package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public class StrongBullet extends AbstractBullet {

    public StrongBullet(Position position, Direction direction) {
        super(position, direction, new ImageView());
    }

    @Override
    public void takeDamage(ITank tank) {
        tank.beDamaged(2);
    }
}
