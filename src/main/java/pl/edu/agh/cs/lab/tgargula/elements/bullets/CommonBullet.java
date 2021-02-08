package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

public class CommonBullet extends AbstractBullet {

    public CommonBullet(Position position, Direction direction) {
        super(position, direction, new ImageView("images/common_bullet.png"),1);
    }

}
