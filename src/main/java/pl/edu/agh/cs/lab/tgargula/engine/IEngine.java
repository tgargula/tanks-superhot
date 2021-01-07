package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public interface IEngine {

    void add(IElement element);

    void remove(IElement element);

    ITank getPlayerTank();

    void changeBullet(KeyEvent event);

    void update(KeyEvent event);

}
