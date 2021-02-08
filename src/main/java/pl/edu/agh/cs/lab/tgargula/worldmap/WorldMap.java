package pl.edu.agh.cs.lab.tgargula.worldmap;

import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Fire;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.*;
import pl.edu.agh.cs.lab.tgargula.elements.powerups.*;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.engine.Engine;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldMap implements IObserver {

    private final List<PlayerTank> players = new LinkedList<>();
    private final Map<Position, IElement> elements = new ConcurrentHashMap<>();
    private final Map<IElement, Position> takenPositions = new ConcurrentHashMap<>();
    private final Set<Position> allPositions;
    private final int size;

    public WorldMap(int size) {
        this.size = size;

        Set<Position> positionSet = new HashSet<>();
        for (int i = 1; i < size - 1; i++)
            for (int j = 1; j < size - 1; j++)
                positionSet.add(Position.of(i, j));
        allPositions = Set.copyOf(positionSet);
    }

    public boolean isOccupied(Position position) {
        return elements.containsKey(position);
    }

    public IElement getElementAt(Position position) {
        return elements.get(position);
    }

    public int getSize() {
        return size;
    }

    public PlayerTank getPlayerTank() {
        return players.get(0);
    }

    private Set<Position> getFreePositions() {
        Set<Position> freePositions = new HashSet<>(allPositions);
        freePositions.removeAll(takenPositions.values());
        return freePositions;
    }

    private Position getRandomFreePosition(boolean withSafeZone) {
        Set<Position> freePositions = getFreePositions();
        if (withSafeZone)
            freePositions.removeAll(getPlayerTank().safeZone());
        return freePositions.stream()
                .skip(new Random().nextInt(freePositions.size()))
                .findFirst()
                .orElse(null);
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

    public void removeBullets() {
        getBulletsAsStream().forEach(this::stopObserving);
    }

    public int removeDestroyedElementsAndGetPoints(Engine engine) {
        AtomicInteger points = new AtomicInteger();
        elements.values().stream()
                .filter(element -> element instanceof IDamageable)
                .map(element -> (IDamageable) element)
                .filter(IDamageable::isDestroyed)
                .forEach(damageable -> {
                    if (damageable instanceof PlayerTank)
                        engine.endGame();
                    else {
                        engine.destroy(damageable);
                        if (damageable instanceof EnemyTank)
                            points.addAndGet(1);
                    }
                });
        return points.get();
    }

    public void createNewEnemyTank() {
        if (getFreePositions().size() > 0)
            observe(new EnemyTank(getRandomFreePosition(true), 1));
    }

    public void createNewObstacle() {
        if (getFreePositions().size() > 0)
            observe(new Obstacle(getRandomFreePosition(false)));
    }

    public void createNewPowerUp(Engine engine) {
        if (getFreePositions().size() > 0) {
            IPowerUp powerUp;
            Position position = getRandomFreePosition(false);
            switch (new Random().nextInt(7)) {
                case 1 -> powerUp = new BouncyBulletPowerUp(engine, position);
                case 2 -> powerUp = new ExtraLifePowerUp(engine, position);
                case 3 -> powerUp = new FastBulletPowerUp(engine, position);
                case 4 -> powerUp = new ImmortalityPowerUp(engine, position);
                case 5 -> powerUp = new StrongBulletPowerUp(engine, position);
                default -> powerUp = new TwoMovesPowerUp(engine, position);
            }
            engine.add(powerUp);
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
