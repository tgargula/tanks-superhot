package pl.edu.agh.cs.lab.tgargula.basics;

import static java.lang.Math.PI;

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

    public static Direction getMoveDirection(double angle) {
        if (3 * PI / 4 < angle || angle <= -3 * PI / 4)
            return Direction.WEST;
        if (angle <= -PI / 4)
            return Direction.NORTH;
        if (angle <= PI / 4)
            return Direction.EAST;
        if (angle <= 3 * PI / 4)
            return Direction.SOUTH;

        throw new IllegalArgumentException();
    }

    public static Direction getShootDirection(double angle) {
        if (7 * PI / 8 < angle || angle <= -7 * PI / 8)
            return Direction.WEST;
        if (angle <= -5 * PI / 8)
            return Direction.NORTHWEST;
        if (angle <= -3 * PI / 8)
            return Direction.NORTH;
        if (angle <= -PI / 8)
            return Direction.NORTHEAST;
        if (angle <= PI / 8)
            return Direction.EAST;
        if (angle <= 3 * PI / 8)
            return Direction.SOUTHEAST;
        if (angle <= 5 * PI / 8)
            return Direction.SOUTH;
        if (angle <= 7 * PI / 8)
            return Direction.SOUTHWEST;

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
