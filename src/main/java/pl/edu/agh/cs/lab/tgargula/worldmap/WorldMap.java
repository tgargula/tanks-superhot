package pl.edu.agh.cs.lab.tgargula.worldmap;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IDamageable;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IMovable;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.engine.IEngine;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WorldMap implements IWorldMap {

    private final List<ITank> players = new LinkedList<>();
    private final Map<Position, IElement> elements = new ConcurrentHashMap<>();
    private final Map<IElement, Position> positions = new ConcurrentHashMap<>();
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
    public ITank getPlayerTank() {
        // TODO
        return players.get(0);
    }

    @Override
    public void nextStep() {
        move();
        takeDamage();
    }

    private void move() {
        Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof IMovable)
                .map(element -> (IMovable) element)
                .filter(element -> !isOccupied(element.nextPosition()))
                .forEach(IMovable::move);
    }

    private void takeDamage() {
        Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof IMovable)
                .map(element -> getElementAt(((IMovable) element).nextPosition()))
                .filter(element -> element instanceof IDamageable)
                .forEach(element -> ((IDamageable) element).beDamaged(1));
    }

    @Override
    public void observe(IElement element) {
        elements.put(element.getPosition(), element);
        positions.put(element, element.getPosition());
        element.addObserver(this);
        if (element instanceof PlayerTank)
            players.add((ITank) element);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public void stopObserving(IElement element) {
        elements.remove(element.getPosition());
        positions.remove(element);
        element.removeObserver(this);
        players.remove(element);
    }

    @Override
    public void changePosition(IElement element) {
        Position oldPosition = positions.get(element);
        Position newPosition = element.getPosition();
        elements.remove(oldPosition);
        elements.put(newPosition, element);
        positions.put(element, newPosition);
    }
}
