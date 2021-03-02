package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

import static pl.edu.agh.cs.lab.tgargula.basics.Direction.*;

public class BouncyBullet extends AbstractBullet {

    public BouncyBullet(Position position, Direction direction) {
        super(position, direction, new ImageView("/images/bullets/bouncy_bullet.png"), 1);
    }

    public void bounce(boolean isVerticalFree, boolean isHorizontalFree) {
        this.direction = switch (direction) {
            case NORTH -> SOUTH;
            case WEST -> EAST;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case NORTHEAST -> isHorizontalFree ^ isVerticalFree ? isVerticalFree ? NORTHWEST : SOUTHEAST : SOUTHWEST;
            case SOUTHEAST -> isHorizontalFree ^ isVerticalFree ? isVerticalFree ? SOUTHWEST : NORTHEAST : NORTHWEST;
            case SOUTHWEST -> isHorizontalFree ^ isVerticalFree ? isVerticalFree ? SOUTHEAST : NORTHWEST : NORTHEAST;
            case NORTHWEST -> isHorizontalFree ^ isVerticalFree ? isVerticalFree ? NORTHEAST : SOUTHWEST : SOUTHEAST;
        };
    }
}
