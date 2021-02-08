package pl.edu.agh.cs.lab.tgargula.elements.bullets;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractMovable;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IDamageable;

public abstract class AbstractBullet extends AbstractMovable implements IBullet {

    protected final int strength;

    protected AbstractBullet(Position position, Direction direction, ImageView imageView, int strength) {
        super(position, direction, imageView);
        this.strength = strength;
    }

    @Override
    public void takeDamage(IDamageable damageable) {
        damageable.beDamaged(strength);
    }

    @Override
    public int getStrength() {
        return strength;
    }
}
