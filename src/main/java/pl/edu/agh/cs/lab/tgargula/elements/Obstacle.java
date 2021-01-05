package pl.edu.agh.cs.lab.tgargula.elements;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IDamageable;

public class Obstacle extends AbstractElement implements IDamageable {

    private final boolean destroyable;

    private int durability = 1;

    public Obstacle(Position position) {
        this(position, true);
    }

    public Obstacle(Position position, boolean destroyable) {
        super(position, new ImageView());
        this.destroyable = destroyable;
    }

    @Override
    public void beDamaged(int damage) {
        if (!destroyable)
            durability -= 1;
        if (durability <= 0)
            beDestroyed();
    }

    @Override
    public void beDestroyed() {
        if (!destroyable) {

        }
    }

}
