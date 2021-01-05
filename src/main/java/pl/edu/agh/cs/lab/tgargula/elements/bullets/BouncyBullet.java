package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

public class BouncyBullet extends AbstractBullet {

    public BouncyBullet(Position position, Direction direction) {
        super(position, direction, new ImageView("/images/bouncy_bullet.png"));
    }

    public void bounce(Position position) {
        this.position = position;
    }
}
