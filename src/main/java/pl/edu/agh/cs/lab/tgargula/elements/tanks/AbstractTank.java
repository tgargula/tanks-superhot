package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractMovable;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

import java.util.Random;

public abstract class AbstractTank extends AbstractMovable implements ITank {

    protected final int maxDurability;
    protected int currentDurability;

    protected AbstractTank(Position position, int durability, ImageView imageView) {
        super(position, Direction.values()[new Random().nextInt(8)], imageView);
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
