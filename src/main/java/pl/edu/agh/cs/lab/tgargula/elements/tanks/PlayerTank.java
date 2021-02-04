package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;

public class PlayerTank extends AbstractTank {

    public PlayerTank(Position position, int durability) {
        super(position, durability, new ImageView("/images/player_tank3.png"));
    }

    @Override
    public void beDamaged(int damage) {

    }

    @Override
    public IBullet shoot() {
        return null;
    }
}
