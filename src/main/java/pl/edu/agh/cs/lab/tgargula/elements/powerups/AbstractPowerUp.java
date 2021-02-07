package pl.edu.agh.cs.lab.tgargula.elements.powerups;

import javafx.scene.image.ImageView;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.AbstractElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IPowerUp;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;

public abstract class AbstractPowerUp extends AbstractElement implements IPowerUp {

    protected final Engine engine;

    protected AbstractPowerUp(Engine engine, Position position) {
        super(position, new ImageView("/images/powerup.png"));
        this.engine = engine;
    }

}
