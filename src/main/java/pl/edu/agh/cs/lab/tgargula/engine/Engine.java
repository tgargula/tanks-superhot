package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.basics.Levels;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Fire;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.bullets.Bullets;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.powerups.PowerUps;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.WorldMap;

import java.util.function.Consumer;

public class Engine {

    private final WorldMap worldMap;
    private final BulletEngine bulletEngine;
    private final StatisticsEngine statisticsEngine;
    private final StepEngine stepEngine;
    private final Consumer<Integer> onEndGame;

    public Engine(WorldMap worldMap, StatisticsEngine statisticsEngine, BulletEngine bulletEngine,
                  Consumer<Integer> onEndGame, Levels level) {
        this.worldMap = worldMap;
        this.statisticsEngine = statisticsEngine;
        this.bulletEngine = bulletEngine;
        this.stepEngine = new StepEngine(this, statisticsEngine, bulletEngine, worldMap,
                new Parameters(10, 10, 10), level);
        this.addObstacles();
        this.onEndGame = onEndGame;
    }

    public void initialize() {
        statisticsEngine.initialize(getPlayerTank().getDurability());
        bulletEngine.choose(Bullets.COMMON);
    }

    public void add(IElement element) {
        worldMap.observe(element);
    }

    public PlayerTank getPlayerTank() {
        return worldMap.getPlayerTank();
    }

    public void update(KeyEvent event) {
        KeyEventListener.update(this, event);

        if (KeyEventListener.isCrucial(event)) {
            stepEngine.run(Event.assignEvents(
                    worldMap.getEnemyTanks(),
                    worldMap.getPlayerTank(),
                    KeyEventListener.getEvent(event)
            ));
        }
    }

    public void addPowerUp(PowerUps powerUp) {
        bulletEngine.addPowerUp(powerUp);
    }

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

    public void destroy(IElement element) {
        element.destroy();
        add(new Fire(element.getPosition()));
    }

    public void endGame() {
        onEndGame.accept(statisticsEngine.getScore());
    }

    public void usePowerUp(PowerUps powerUp) {
        if (bulletEngine.isAvailable(powerUp))
            stepEngine.usePowerUp(powerUp);
    }
}
