package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public class KeyEventListener {

    public static boolean isCrucial(KeyEvent event) {
        return switch (event.getCode().toString()) {
            case "W", "A", "S", "D", "SPACE", "LEFT", "RIGHT", "UP", "DOWN" -> true;
            default -> false;
        };
    }

    public static void update(IEngine engine, KeyEvent event) {
        ITank playerTank = engine.getPlayerTank();
        switch (event.getCode().toString()) {
            case "W", "UP" -> playerTank.setDirection(Direction.NORTH);
            case "D", "RIGHT" -> playerTank.setDirection(Direction.EAST);
            case "S", "DOWN" -> playerTank.setDirection(Direction.SOUTH);
            case "A", "LEFT" -> playerTank.setDirection(Direction.WEST);
            case "SPACE" -> playerTank.shoot();
            case "C" -> playerTank.rotateRight();
            case "Z" -> playerTank.rotateLeft();
            case "E", "Q", "1", "2", "3", "4" -> engine.changeBullet(event);
        }
    }

}