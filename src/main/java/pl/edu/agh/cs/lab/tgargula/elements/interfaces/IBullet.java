package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

public interface IBullet extends IElement {

    void move();

    void takeDamage(ITank tank);

}
