package pl.edu.agh.cs.lab.tgargula.worldmap;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.*;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldMap implements IWorldMap {

    private final List<PlayerTank> players = new LinkedList<>();
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
    public PlayerTank getPlayerTank() {
        return players.get(0);
    }

    public List<ITank> getEnemyTanks() {
        return Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof EnemyTank)
                .map(element -> (ITank) element)
                .collect(Collectors.toList());
    }

    public Stream<IBullet> getBulletsAsStream() {
        return Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof IBullet)
                .map(element -> (IBullet) element);
    }

    public void updateBullets(Map<Position, IBullet> newBullets) {
        getBulletsAsStream().forEach(this::stopObserving);
        newBullets.forEach((position, bullet) -> {
            bullet.setPosition(position);
            this.observe(bullet);
        });
    }

    @Override
    public void nextStep() {
        takeDamage();
        setDirectionOfEnemyTanks();
        move();
    }

    private void move() {
        Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof IMovable && !(element instanceof PlayerTank))
                .map(element -> (IMovable) element)
                .filter(element -> !isOccupied(element.nextPosition()))
                .forEach(IMovable::move);
    }

    private void setDirectionOfEnemyTanks() {
        PlayerTank playerTank = getPlayerTank();
        Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof EnemyTank)
                .map(element -> (EnemyTank) element)
                .forEach(tank -> tank.changeDirection(playerTank));
    }

    private void takeDamage() {
        Set.copyOf(elements.values()).stream()
                .filter(element -> element instanceof IBullet)
                .map(element -> (IBullet) element)
                .filter(bullet -> isOccupied(bullet.nextPosition()))
                .filter(bullet -> getElementAt(bullet.nextPosition()) instanceof IDamageable)
                .forEach(bullet -> {
                    bullet.takeDamage((IDamageable) getElementAt(bullet.nextPosition()));
                    bullet.destroy();
                });
    }

    @Override
    public void observe(IElement element) {
        elements.put(element.getPosition(), element);
        positions.put(element, element.getPosition());
        element.addObserver(this);
        if (element instanceof PlayerTank)
            players.add((PlayerTank) element);
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
