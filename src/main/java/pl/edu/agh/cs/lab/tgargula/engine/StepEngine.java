package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.geometry.Pos;
import javafx.stage.PopupWindow;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.basics.HashSetHashMap;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.basics.SetMap;
import pl.edu.agh.cs.lab.tgargula.elements.Fire;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.BouncyBullet;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.Bullets;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.StrongBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.*;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.EnemyTank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.settings.Levels;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StepEngine {

    private final WorldMap worldMap;
    private final Engine engine;
    private final StatisticsEngine statisticsEngine;
    private final BulletEngine bulletEngine;
    private final Levels level = Levels.MEDIUM;

    public StepEngine(Engine engine, StatisticsEngine statisticsEngine, BulletEngine bulletEngine, WorldMap worldMap) {
        this.worldMap = worldMap;
        this.engine  = engine;
        this.statisticsEngine = statisticsEngine;
        this.bulletEngine = bulletEngine;
    }

    public void run(SetMap<Event, ITank> events) {
        worldMap.removeFire();

        SetMap<Position, IBullet> setMap = new HashSetHashMap<>();
        worldMap.getBulletsAsStream().forEach(bullet -> setMap.put(bullet.nextPosition(), bullet));
        worldMap.removeBullets();


        moveTanks(events.get(Event.MOVE));

        moveBullets(setMap);

        takeShots(events.get(Event.SHOOT));

        int points = worldMap.removeDestroyedElementsAndGetPoints(engine);
        createNewObjects();

        this.statisticsEngine.updateScore(points);
    }

    private void moveTanks(Set<ITank> tanks) {

        if (tanks == null) return;

        PlayerTank playerTank = worldMap.getPlayerTank();

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
                IBullet bullet = (IBullet) element;
                bullet.takeDamage(tank);
                if (tank instanceof PlayerTank)
                    statisticsEngine.removeHeart(bullet.getStrength());
                bullet.destroy();
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
                .forEach(position -> engine.add(new Fire(position)));

        Map<Position, IBullet> notDestructedBullets = new HashMap<>();

        // Take damage
        bullets.forEach((position, bullet) -> {
            if (worldMap.isOccupied(position)) {
                IElement element = worldMap.getElementAt(position);
                if (bullet instanceof BouncyBullet && element instanceof Obstacle) {
                    ((BouncyBullet) bullet).bounce(isVerticalFree(bullet), isHorizontalFree(bullet));
                    notDestructedBullets.put(bullet.nextPosition(), bullet);
                } else if (element instanceof IDamageable) {
                    bullet.takeDamage((IDamageable) element);
                    if (element instanceof Obstacle && bullet instanceof StrongBullet && ((Obstacle) element).isDestroyed())
                        notDestructedBullets.put(position, bullet);
                } else if (element instanceof IPowerUp) {
                    element.destroy();
                    notDestructedBullets.put(position, bullet);
                }
                if (element instanceof PlayerTank)
                    statisticsEngine.removeHeart(bullet.getStrength());
            } else
                notDestructedBullets.put(position, bullet);
        });

        // Apply moves
        worldMap.updateBullets(notDestructedBullets);
    }

    private void takeShots(Set<ITank> tanks) {

        if (tanks == null) return;

        // Rotate enemy tanks towards player
        tanks.stream()
                .filter(tank -> tank instanceof EnemyTank)
                .forEach(tank -> ((EnemyTank) tank).changeDirection(engine.getPlayerTank(), Event.SHOOT));

        tanks.forEach(tank -> {
            Position position = tank.nextPosition();
            IBullet bullet = tank.shoot(Bullets.get(bulletEngine, tank));
            if (worldMap.isOccupied(position)) {
                IElement element = worldMap.getElementAt(position);
                if (element instanceof IDamageable) {
                    IDamageable damageable = (IDamageable) element;
                    bullet.takeDamage(damageable);
                    if (damageable.isDestroyed() && !(damageable instanceof PlayerTank)) {
                        statisticsEngine.updateScore(1);
                        engine.destroy(damageable);
                        if (damageable instanceof Obstacle && bullet instanceof StrongBullet)
                            engine.add(bullet);
                    }
                } else if (element instanceof IBullet || element instanceof IPowerUp) engine.destroy(element);
            } else engine.add(bullet);
        });
    }

    private void createNewObjects() {
        if (Levels.draw(level.getEnemyTankProbability()) || worldMap.getEnemyTanks().isEmpty())
            worldMap.createNewEnemyTank();
        if (Levels.draw(level.getObstacleProbability())) worldMap.createNewObstacle();
        if (Levels.draw(level.getPowerUpProbability())) worldMap.createNewPowerUp(engine);
    }

    private boolean isVerticalFree(IMovable movable) {
        Position position = movable.getPosition();
        return switch (movable.getDirection()) {
            case NORTH, WEST, SOUTH, EAST -> true;
            case NORTHWEST, NORTHEAST -> !worldMap.isOccupied(position.add(Direction.NORTH.toUnitVector()));
            case SOUTHWEST, SOUTHEAST -> !worldMap.isOccupied(position.add(Direction.SOUTH.toUnitVector()));
        };
    }

    private boolean isHorizontalFree(IMovable movable) {
        Position position = movable.getPosition();
        return switch (movable.getDirection()) {
            case NORTH, WEST, SOUTH, EAST -> true;
            case NORTHWEST, SOUTHWEST -> !worldMap.isOccupied(position.add(Direction.WEST.toUnitVector()));
            case NORTHEAST, SOUTHEAST -> !worldMap.isOccupied(position.add(Direction.EAST.toUnitVector()));
        };
    }

}
