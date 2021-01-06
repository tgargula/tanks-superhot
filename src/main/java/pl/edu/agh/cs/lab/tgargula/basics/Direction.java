package pl.edu.agh.cs.lab.tgargula.basics;

import javafx.scene.input.KeyEvent;
import pl.edu.agh.cs.lab.tgargula.elements.interfaces.ITank;

public enum Direction {
    EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH, NORTHEAST;

    public Position toUnitVector() {
        return switch (this) {
            case EAST -> Position.of(1, 0);
            case SOUTHEAST -> Position.of(1, 1);
            case SOUTH -> Position.of(0, 1);
            case SOUTHWEST -> Position.of(-1, 1);
            case WEST -> Position.of(-1, 0);
            case NORTHWEST -> Position.of(-1, -1);
            case NORTH -> Position.of(0, -1);
            case NORTHEAST -> Position.of(1, -1);
        };
    }

    public int getAngle() {
        return switch (this) {
            case EAST -> 0;
            case SOUTHEAST -> 45;
            case SOUTH -> 90;
            case SOUTHWEST -> 135;
            case WEST -> 180;
            case NORTHWEST -> 225;
            case NORTH -> 270;
            case NORTHEAST -> 315;
        };
    }

    // Move ?
    public static void processEvent(ITank player, KeyEvent event) {
        switch (event.getCode().toString()) {
            case "LEFT" -> player.move(Direction.WEST);
            case "DOWN" -> player.move(Direction.SOUTH);
            case "RIGHT" -> player.move(Direction.EAST);
            case "UP" -> player.move(Direction.NORTH);
            case "W" -> player.setDirection(Direction.NORTH);
            case "E" -> player.setDirection(Direction.NORTHEAST);
            case "D" -> player.setDirection(Direction.EAST);
            case "C" -> player.setDirection(Direction.SOUTHEAST);
            case "X" -> player.setDirection(Direction.SOUTH);
            case "Z" -> player.setDirection(Direction.SOUTHWEST);
            case "A" -> player.setDirection(Direction.WEST);
            case "Q" -> player.setDirection(Direction.NORTHWEST);
        }
    }
}
