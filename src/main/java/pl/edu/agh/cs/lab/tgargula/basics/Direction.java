package pl.edu.agh.cs.lab.tgargula.basics;

import static java.lang.Math.PI;
import static java.lang.Math.abs;

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

    private static Direction draw(Direction first, Direction second) {
        return Math.random() < 0.5 ? first : second;
    }

    public static Direction getMoveDirection(Position relativePosition) {
        int x = relativePosition.x;
        int y = relativePosition.y;

        if (x > abs(y)) return EAST;
        if (y > abs(x)) return SOUTH;
        if (-x > abs(y)) return WEST;
        if (-y > abs(x)) return NORTH;
        if (x == y)
            if (x > 0) return draw(SOUTH, EAST);
            else return draw(NORTH, WEST);
        else
            if (x > 0) return draw(NORTH, EAST);
            else return draw(SOUTH, WEST);
    }

    public static Direction getShootDirection(double angle) {
        if (7 * PI / 8 < angle || angle <= -7 * PI / 8)
            return WEST;
        if (angle <= -5 * PI / 8)
            return NORTHWEST;
        if (angle <= -3 * PI / 8)
            return NORTH;
        if (angle <= -PI / 8)
            return NORTHEAST;
        if (angle <= PI / 8)
            return EAST;
        if (angle <= 3 * PI / 8)
            return SOUTHEAST;
        if (angle <= 5 * PI / 8)
            return SOUTH;
        if (angle <= 7 * PI / 8)
            return SOUTHWEST;

        throw new IllegalArgumentException();
    }

    public Direction rotateLeft() {
        return rotate(NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH);
    }

    public Direction rotateRight() {
        return rotate(SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH, NORTHEAST, EAST);
    }

    private Direction rotate(Direction mapEast, Direction mapSoutheast, Direction mapSouth, Direction mapSouthwest,
                             Direction mapWest, Direction mapNorthwest, Direction mapNorth, Direction mapNortheast) {
        return switch (this) {
            case EAST -> mapEast;
            case SOUTHEAST -> mapSoutheast;
            case SOUTH -> mapSouth;
            case SOUTHWEST -> mapSouthwest;
            case WEST -> mapWest;
            case NORTHWEST -> mapNorthwest;
            case NORTH -> mapNorth;
            case NORTHEAST -> mapNortheast;
        };
    }
}
