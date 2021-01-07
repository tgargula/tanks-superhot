package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class Engine implements IEngine {

    private final IWorldMap worldMap;

    public Engine(IWorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @Override
    public void add(IElement element) {
        worldMap.observe(element);
    }

    @Override
    public void remove(IElement element) {
        worldMap.stopObserving(element);
    }

    @Override
    public ITank getPlayerTank() {
        return worldMap.getPlayerTank();
    }

    @Override
    public void update(KeyEvent event) {
        KeyEventListener.update(this, event);
        if (KeyEventListener.isCrucial(event))
            worldMap.nextStep();
    }

    @Override
    public void changeBullet(KeyEvent event) {

    }
}
