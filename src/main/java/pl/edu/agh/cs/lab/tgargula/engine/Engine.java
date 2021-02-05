package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.basics.HashSetHashMap;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.basics.SetMap;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IMovable;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

import java.util.Set;

public class Engine implements IEngine {

    private final WorldMap worldMap;
    private IBullet selectedBullet;

    public Engine(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.addObstacles();
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
    public PlayerTank getPlayerTank() {
        return worldMap.getPlayerTank();
    }

    @Override
    public void update(KeyEvent event) {
        KeyEventListener.update(this, event);

        if (KeyEventListener.isCrucial(event)) {
            SetMap<Event, ITank> events = Event.assignEvents(
                    worldMap.getEnemyTanks(),
                    getPlayerTank(),
                    KeyEventListener.getEvent(event)
            );

            if (events.containsKey(Event.MOVE)) moveTanks(events.get(Event.MOVE));
            moveBullets();
            if (events.containsKey(Event.SHOOT)) takeShots(events.get(Event.SHOOT));
            createNewObjects();
        }
    }

    private void moveTanks(Set<ITank> tanks) {
        PlayerTank playerTank = getPlayerTank();
        tanks.stream()
                .filter(tank -> tank instanceof EnemyTank)
                .map(tank -> (EnemyTank) tank)
                .forEach(tank -> tank.changeDirection(playerTank));

        tanks.stream()
                .filter(tank -> !worldMap.isOccupied(tank.nextPosition()))
                .forEach(IMovable::move);
    }

    private void moveBullets() {
        SetMap<Position, IBullet> setMap = new HashSetHashMap<>();
        worldMap.getBulletsAsStream().forEach(bullet -> setMap.put(bullet.nextPosition(), bullet));
        setMap.forEach(((position, bullet) -> {
            if (setMap.get(position).size() > 1)
                setMap.remove(position);
        }));
    }

    private void takeShots(Set<ITank> tanks) {
        tanks.stream()
                .filter(tank -> !worldMap.isOccupied(tank.nextPosition()))
                .map(ITank::shoot)
                .forEach(this::add);


    }

    private void createNewObjects() {

    }

    @Override
    public void changeBullet(KeyEvent event) {

    }

    private void addObstacles() {
        int size = worldMap.getSize();

        // toss edges with indestructible obstacles
        for (int i = 0; i < size - 1; i++) {
            add(new Obstacle(Position.of(0, i), false));
            add(new Obstacle(Position.of(size - 1, i + 1), false));
            add(new Obstacle(Position.of(i + 1, 0), false));
            add(new Obstacle(Position.of(i, size - 1), false));
        }
    }
}
