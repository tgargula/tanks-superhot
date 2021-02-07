package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

public interface ITank extends IDamageable, IMovable {

    IBullet shoot();

    void rotateLeft();

    void rotateRight();

}
