package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;

public interface ITank extends IDamageable, IMovable {

    void move(Direction direction);

    IBullet shoot();

    void rotateLeft();

    void rotateRight();

}
