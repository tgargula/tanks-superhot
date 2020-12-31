package pl.edu.agh.cs.lab.tgargula.elements;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IDamageable;

public class Obstacle extends AbstractElement implements IDamageable {

    public Obstacle(Position position) {
        super(position);
    }

    @Override
    public void beDamaged(int damage) {

    }

    @Override
    public void beDestroyed() {

    }

}
