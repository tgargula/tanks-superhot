package pl.edu.agh.cs.lab.tgargula.elements.powerups;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;

public class ExtraLifePowerUp extends AbstractPowerUp {

    public ExtraLifePowerUp(Engine engine, Position position) {
        super(engine, position);
    }

    @Override
    public void use() {
        engine.addHeart();
    }
}
