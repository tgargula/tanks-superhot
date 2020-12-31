package pl.edu.agh.cs.lab.tgargula.worldmap;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class WorldMap implements IWorldMap {

    private final int size;

    public WorldMap(int size) {
        this.size = size;
    }

    @Override
    public IElement getElementAt(Position position) {
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }
}
