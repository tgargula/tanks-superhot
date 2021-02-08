package pl.edu.agh.cs.lab.tgargula.elements.powerups;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;

public class FastBulletPowerUp extends AbstractPowerUp {

    public FastBulletPowerUp(Engine engine, Position position) {
        super(engine, position);
    }

    @Override
    public void use() {
        engine.addPowerUp(PowerUps.FAST_BULLET);
    }
}
