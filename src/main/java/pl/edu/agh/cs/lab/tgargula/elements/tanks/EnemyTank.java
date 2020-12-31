package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;

public class EnemyTank extends AbstractTank {

    public EnemyTank(Position position, int durability) {
        super(position, durability);
    }

    @Override
    public void beDamaged(int damage) {

    }

    @Override
    public void beDestroyed() {

    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public IBullet shoot() {
        return null;
    }
}
