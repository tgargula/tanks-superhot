package pl.edu.agh.cs.lab.tgargula.basics;

public enum Direction {
    NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST;

    public Position toUnitVector() {
        return switch (this) {
            case NORTH -> Position.of(0, 1);
            case NORTHEAST -> Position.of(1, 1);
            case EAST -> Position.of(1, 0);
            case SOUTHEAST -> Position.of(1, -1);
            case SOUTH -> Position.of(0, -1);
            case SOUTHWEST -> Position.of(-1, -1);
            case WEST -> Position.of(-1, 0);
            case NORTHWEST -> Position.of(-1, 1);
        };
    }
}
