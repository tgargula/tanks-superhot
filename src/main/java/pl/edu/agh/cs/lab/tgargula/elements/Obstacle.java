package pl.edu.agh.cs.lab.tgargula.elements;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IDamageable;

public class Obstacle extends AbstractElement implements IDamageable {

    private int durability;

    public Obstacle(Position position) {
        this(position, true);
    }

    public Obstacle(Position position, boolean destroyable) {
        super(
                position,
                destroyable ? new ImageView("/images/obstacle2.png") : new ImageView("/images/obstacle3.png")
        );
        this.durability = destroyable ? 2 : Integer.MAX_VALUE;
    }

    @Override
    public void beDamaged(int damage) {
        durability -= damage;
        if (durability == 2)
            this.imageView = new ImageView("/images/obstacle2.png");
        if (durability == 1)
            this.imageView = new ImageView("/images/obstacle1.png");
    }

    @Override
    public boolean isDestroyed() {
        return this.durability <= 0;
    }

}
