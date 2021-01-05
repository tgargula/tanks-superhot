package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public class BouncyBullet extends AbstractBullet {

    public BouncyBullet(Position position, Direction direction) {
        super(position, direction, new ImageView());
    }

    public void bounce(Position position) {
        this.position = position;
    }
}
