package pl.edu.agh.cs.lab.tgargula.elements.powerups;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;

public class BouncyBulletPowerUp extends AbstractPowerUp {

    public BouncyBulletPowerUp(Engine engine, Position position) {
        super(engine, position);
    }

    @Override
    public void use() {
        engine.addPowerUp(PowerUps.BOUNCY_BULLET);
    }

}
