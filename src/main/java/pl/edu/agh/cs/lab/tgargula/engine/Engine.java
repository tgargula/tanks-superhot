package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.basics.Position;
import pl.edu.agh.cs.lab.tgargula.elements.Obstacle;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IBullet;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.IElement;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;
import pl.edu.agh.cs.lab.tgargula.elements.tanks.PlayerTank;
import pl.edu.agh.cs.lab.tgargula.worldmap.interfaces.IWorldMap;

public class Engine implements IEngine {

    private final IWorldMap worldMap;
    private IBullet selectedBullet;

    public Engine(IWorldMap worldMap) {
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
        return (PlayerTank) worldMap.getPlayerTank();
    }

    @Override
    public void update(KeyEvent event) {
        KeyEventListener.update(this, event);
        PlayerTank playerTank = getPlayerTank();
        if (KeyEventListener.isCrucial(event)) {
            if (KeyEventListener.isShot(event)) {
                worldMap.nextStep();
                add(playerTank.shoot());
            }
            else {
                if (!worldMap.isOccupied(playerTank.nextPosition()))
                    playerTank.move();
                worldMap.nextStep();
            }
        }
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
