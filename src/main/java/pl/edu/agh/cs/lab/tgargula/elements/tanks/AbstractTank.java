package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public abstract class AbstractTank extends AbstractElement implements ITank {

    protected final int maxDurability;

    protected int currentDurability;

    protected AbstractTank(Position position, int durability, ImageView imageView) {
        super(position, imageView);
        this.maxDurability = durability;
        this.currentDurability = durability;
    }

    @Override
    public void beDamaged(int damage) {
        this.currentDurability -= damage;
        if (currentDurability <= 0) beDestroyed();
    }

    @Override
    public void beDestroyed() {
    }
}
