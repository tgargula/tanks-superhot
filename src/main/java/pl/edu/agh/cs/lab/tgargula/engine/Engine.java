package pl.edu.agh.cs.lab.tgargula.engine;

import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
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
}
