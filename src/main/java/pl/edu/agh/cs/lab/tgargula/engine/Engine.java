package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.basics.HashSetHashMap;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.basics.SetMap;
import pl.edu.agh.cs.lab.tgargula.elements.Fire;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.Bullets;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.*;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Engine implements IEngine {

    private final WorldMap worldMap;
    private final StatisticsEngine statisticsEngine;
    private final BulletEngine bulletEngine;

    public Engine(WorldMap worldMap, StatisticsEngine statisticsEngine, BulletEngine bulletEngine) {
        this.worldMap = worldMap;
        this.statisticsEngine = statisticsEngine;
        this.bulletEngine = bulletEngine;
        this.addObstacles();
    }

    public void initialize() {
        statisticsEngine.initialize(getPlayerTank().getDurability());
        bulletEngine.choose(Bullets.COMMON);
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
            worldMap.removeFire();

            SetMap<Event, ITank> events = Event.assignEvents(
                    worldMap.getEnemyTanks(),
                    getPlayerTank(),
                    KeyEventListener.getEvent(event)
            );

            SetMap<Position, IBullet> setMap = new HashSetHashMap<>();
            worldMap.getBulletsAsStream().forEach(bullet -> setMap.put(bullet.nextPosition(), bullet));
            worldMap.removeBullets();

            if (events.containsKey(Event.MOVE))
                moveTanks(events.get(Event.MOVE));

            moveBullets(setMap);

            if (events.containsKey(Event.SHOOT))
                takeShots(events.get(Event.SHOOT));

            int points = worldMap.removeDestroyedElementsAndGetPoints();
            createNewObjects();

            this.statisticsEngine.updateScore(10 + points);
        }
    }

    private void moveTanks(Set<ITank> tanks) {
        PlayerTank playerTank = getPlayerTank();

        // Change direction of enemy tanks
        tanks.stream()
                .filter(tank -> tank instanceof EnemyTank)
                .map(tank -> (EnemyTank) tank)
                .forEach(tank -> tank.changeDirection(playerTank, Event.MOVE));

        // Move tanks if the space is not occupied
        tanks.forEach(tank -> {
            IElement element = worldMap.getElementAt(tank.nextPosition());
            if (element instanceof IPowerUp) {
                if (tank instanceof PlayerTank)
                    ((IPowerUp) element).use();
                element.destroy();
            }
            if (element instanceof IBullet) {
                ((IBullet) element).takeDamage(tank);
                element.destroy();
            }
            if (!(element instanceof Obstacle || element instanceof ITank))
                tank.move();
        });
    }

    private void moveBullets(SetMap<Position, IBullet> setMap) {
        Map<Position, IBullet> bullets = setMap.flatten();
        Set<Position> positionsWithFire = setMap.keySet();
        positionsWithFire.removeAll(bullets.keySet());
        positionsWithFire.stream()
                .filter(position -> !worldMap.isOccupied(position))
                .forEach(position -> add(new Fire(position)));

        Map<Position, IBullet> notDestructedBullets = new HashMap<>();

        // Take damage
        bullets.forEach((position, bullet) -> {
            if (worldMap.isOccupied(position)) {
                IElement element = worldMap.getElementAt(position);
                if (element instanceof IDamageable)
                    bullet.takeDamage((IDamageable) element);
                if (element instanceof PlayerTank)
                    statisticsEngine.removeHeart();
            } else notDestructedBullets.put(position, bullet);
        });

        // Apply moves
        worldMap.updateBullets(notDestructedBullets);
    }

    private void takeShots(Set<ITank> tanks) {

        // Rotate enemy tanks towards player
        tanks.stream()
                .filter(tank -> tank instanceof EnemyTank)
                .forEach(tank -> ((EnemyTank) tank).changeDirection(getPlayerTank(), Event.SHOOT));

        tanks.forEach(tank -> {
            Position position = tank.nextPosition();
            IBullet bullet = tank.shoot(Bullets.get(bulletEngine, tank));
            if (worldMap.isOccupied(position)) {
                IElement element = worldMap.getElementAt(position);
                if (element instanceof IDamageable) {
                    IDamageable damageable = (IDamageable) element;
                    bullet.takeDamage(damageable);
                    if (damageable.isDestroyed()) {
                        damageable.destroy();
                        add(new Fire(position));
                    }
                }
            } else add(bullet);

        });

        // Add bullets to the map if the field is not occupied
//        tanks.stream()
//                .filter(tank -> !worldMap.isOccupied(tank.nextPosition()))
//                .map(tank -> tank.shoot(Bullets.get(bulletEngine, tank))
//                .forEach(this::add);
//
//        // Otherwise take instant damage
//        tanks.stream()
//                .filter(tank -> worldMap.isOccupied(tank.nextPosition()))
//                .filter(tank -> worldMap.getElementAt(tank.nextPosition()) instanceof IDamageable)
//                .map(tank -> tank.shoot(tank instanceof PlayerTank ? bulletEngine.getChosenBullet() : Bullets.getRandom()))
//                .forEach(bullet -> {
//                    IDamageable element = (IDamageable) worldMap.getElementAt(bullet.getPosition());
//                    bullet.takeDamage(element);
//                    if (element.isDestroyed()) {
//                        element.destroy();
//                        add(new Fire(element.getPosition()));
//                    }
//                });
    }

    private void createNewObjects() {
        worldMap.createNewEnemyTank();
//        worldMap.createNewObstacle();
//        worldMap.createNewPowerUp(this);
    }

    public void addBullet(Bullets bullet) {
        bulletEngine.addBullet(bullet);
    }

    @Override
    public void changeBullet(KeyEvent event) {
        switch (event.getCode().toString()) {
            case "Q" -> bulletEngine.previousBullet();
            case "E" -> bulletEngine.nextBullet();
            case "DIGIT1" -> bulletEngine.choose(Bullets.COMMON);
            case "DIGIT2" -> bulletEngine.choose(Bullets.BOUNCY);
            case "DIGIT3" -> bulletEngine.choose(Bullets.FAST);
            case "DIGIT4" -> bulletEngine.choose(Bullets.STRONG);
        }
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

    public void addHeart() {
        getPlayerTank().addHeart();
        statisticsEngine.addHeart();
    }
}
