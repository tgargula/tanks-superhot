package pl.edu.agh.cs.lab.tgargula.elements.tanks;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.engine.Event;

public class EnemyTank extends AbstractTank {

    private final EnemyTankAI tankAI = new EnemyTankAI(this);

    public EnemyTank(Position position, int durability) {
        super(position, durability, new ImageView("/images/tanks/enemy_tank.png"));
    }

    public void changeDirection(PlayerTank player, Event event) {
        if (event.equals(Event.MOVE))
            this.direction = tankAI.bestMoveDirection(player);
        else if (event.equals(Event.SHOOT))
            this.direction = tankAI.bestShootDirection(player);
    }
}
