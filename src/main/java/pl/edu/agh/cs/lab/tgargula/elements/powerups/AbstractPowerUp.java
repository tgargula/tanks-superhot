package pl.edu.agh.cs.lab.tgargula.elements.powerups;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IPowerUp;

public abstract class AbstractPowerUp extends AbstractElement implements IPowerUp {

    protected AbstractPowerUp(Position position) {
        super(position);
    }

}
