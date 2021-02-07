package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

import pl.edu.agh.cs.lab.tgargula.elements.bullets.Bullets;

public interface ITank extends IDamageable, IMovable {

    IBullet shoot(Bullets bulletType);

    void rotateLeft();

    void rotateRight();

}
