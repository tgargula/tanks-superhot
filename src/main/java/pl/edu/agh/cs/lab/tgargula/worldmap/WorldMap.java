package pl.edu.agh.cs.lab.tgargula.worldmap;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IObserver;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorldMap implements IWorldMap {

    private final Map<Position, IElement> elements = new ConcurrentHashMap<>();
    private final int size;

    public WorldMap(int size) {
        this.size = size;
    }

    @Override
    public boolean isOccupied(Position position) {
        return elements.containsKey(position);
    }

    @Override
    public IElement getElementAt(Position position) {
        return elements.get(position);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void observe(IElement element) {
        elements.put(element.getPosition(), element);
    }

    @Override
    public void stopObserving(IElement element) {
        elements.remove(element.getPosition());
    }
}
