package pl.edu.agh.cs.lab.tgargula.engine;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.basics.Direction;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public class KeyEventListener {

    public static Event getEvent(KeyEvent keyEvent) {
        return switch(keyEvent.getCode().toString()) {
            case "W", "A", "S", "D", "LEFT", "RIGHT", "UP", "DOWN" -> Event.MOVE;
            case "SPACE" -> Event.SHOOT;
            default -> Event.NULL;
        };
    }

    public static boolean isCrucial(KeyEvent keyEvent) {
        return !getEvent(keyEvent).equals(Event.NULL);
    }

    public static void update(IEngine engine, KeyEvent event) {
        ITank playerTank = engine.getPlayerTank();
        switch (event.getCode().toString()) {
            case "W", "UP" -> playerTank.setDirection(Direction.NORTH);
            case "D", "RIGHT" -> playerTank.setDirection(Direction.EAST);
            case "S", "DOWN" -> playerTank.setDirection(Direction.SOUTH);
            case "A", "LEFT" -> playerTank.setDirection(Direction.WEST);
            case "C" -> playerTank.rotateRight();
            case "Z" -> playerTank.rotateLeft();
            case "E", "Q", "DIGIT1", "DIGIT2", "DIGIT3", "DIGIT4" -> engine.changeBullet(event);
        }
        System.out.println(event.getCode());
    }
}
