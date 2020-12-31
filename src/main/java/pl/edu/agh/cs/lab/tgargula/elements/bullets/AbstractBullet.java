package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public abstract class AbstractBullet extends AbstractElement implements IBullet {

    protected AbstractBullet(Position position) {
        super(position);
    }

    @Override
    public void move() {

    }

    @Override
    public void takeDamage(ITank tank) {

    }
}
