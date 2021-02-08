package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

public class StrongBullet extends AbstractBullet {

    public StrongBullet(Position position, Direction direction) {
        super(position, direction, new ImageView("/images/strong_bullet.png"), 2);
    }

}
