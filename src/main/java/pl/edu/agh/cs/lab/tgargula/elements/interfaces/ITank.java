package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;

public interface ITank extends IDamageable, IMovable {

    IBullet shoot();

    void rotateLeft();

    void rotateRight();

}
