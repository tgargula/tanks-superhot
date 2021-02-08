package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;

public interface IMovable extends IElement {

    void setDirection(Direction direction);

    void setPosition(Position position);

    Position nextPosition();

    Direction getDirection();

    void move();

}
