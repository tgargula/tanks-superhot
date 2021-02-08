package pl.edu.agh.cs.lab.tgargula.elements.interfaces;

public interface IBullet extends IMovable {

    void takeDamage(IDamageable tank);

    int getStrength();

}
