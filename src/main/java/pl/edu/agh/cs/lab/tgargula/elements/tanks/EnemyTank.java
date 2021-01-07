package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;

public class EnemyTank extends AbstractTank {

    public EnemyTank(Position position, int durability) {
        super(position, durability, new ImageView("/images/enemy_tank.png"));
    }

    @Override
    public void beDamaged(int damage) {

    }

    @Override
    public void beDestroyed() {

    }

    @Override
    public IBullet shoot() {
        return null;
    }
}
