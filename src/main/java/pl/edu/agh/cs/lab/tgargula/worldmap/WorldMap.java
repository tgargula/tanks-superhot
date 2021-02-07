package pl.edu.agh.cs.lab.tgargula.worldmap;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Fire;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.*;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldMap implements IWorldMap {

    private final List<PlayerTank> players = new LinkedList<>();
    private final Map<Position, IElement> elements = new ConcurrentHashMap<>();
    private final Map<IElement, Position> takenPositions = new ConcurrentHashMap<>();
    private final Set<Position> availablePositions;
    private final int size;

    public WorldMap(int size) {
        this.size = size;

        Set<Position> positionSet = new HashSet<>();
        for (int i = 1; i < size - 1; i++)
            for (int j = 1; j < size - 1; j++)
                positionSet.add(Position.of(i, j));
        availablePositions = Set.copyOf(positionSet);
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

    public void removeFire() {
        elements.values().stream()
                .filter(element -> element instanceof Fire)
                .forEach(IObservable::destroy);
    }

    public void removeDestroyedElements() {
        elements.values().stream()
                .filter(element -> element instanceof IDamageable)
                .map(element -> (IDamageable) element)
                .filter(IDamageable::isDestroyed)
                .forEach(damageable -> {
                    damageable.destroy();
                    observe(new Fire(damageable.getPosition()));
                });
    }

    public void createNewEnemyTank() {
        Set<Position> positions = new HashSet<>(availablePositions);
        positions.removeAll(takenPositions.values());
        System.out.println(positions.size());
        if (positions.size() > 0) {
            Position position = positions.stream().skip(new Random().nextInt(positions.size())).findFirst().orElse(null);
            observe(new EnemyTank(position, 1));
        }
    }

    @Override
    public void observe(IElement element) {
        elements.put(element.getPosition(), element);
        takenPositions.put(element, element.getPosition());
        element.addObserver(this);
        if (element instanceof PlayerTank)
            players.add((PlayerTank) element);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public void stopObserving(IElement element) {
        elements.remove(element.getPosition());
        takenPositions.remove(element);
        element.removeObserver(this);
        players.remove(element);
    }

    @Override
    public void changePosition(IElement element) {
        Position oldPosition = takenPositions.get(element);
        Position newPosition = element.getPosition();
        elements.remove(oldPosition);
        elements.put(newPosition, element);
        takenPositions.put(element, newPosition);
    }
}
