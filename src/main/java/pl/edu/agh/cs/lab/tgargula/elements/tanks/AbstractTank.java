package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.*;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractMovable;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

import java.util.Random;

public abstract class AbstractTank extends AbstractMovable implements ITank {

    protected final int maxDurability;
    protected int currentDurability;

    protected AbstractTank(Position position, int durability, ImageView imageView) {
        super(position, Direction.values()[new Random().nextInt(Direction.values().length)], imageView);
        this.maxDurability = durability;
        this.currentDurability = durability;
    }

    @Override
    public void beDamaged(int damage) {
        this.currentDurability -= damage;
    }

    @Override
    public boolean isDestroyed() {
        return this.currentDurability <= 0;
    }

    @Override
    public IBullet shoot(Bullets bulletType) {
        return switch (bulletType) {
            case COMMON -> new CommonBullet(nextPosition(), direction);
            case BOUNCY -> new BouncyBullet(nextPosition(), direction);
            case FAST -> new FastBullet(nextPosition(), direction);
            case STRONG -> new StrongBullet(nextPosition(), direction);
        };
    }

    @Override
    public void rotateLeft() {
        this.direction = direction.rotateLeft();
    }

    @Override
    public void rotateRight() {
        this.direction = direction.rotateRight();
    }

}
