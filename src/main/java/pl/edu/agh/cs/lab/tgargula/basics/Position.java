package pl.edu.agh.cs.lab.tgargula.basics;

import java.util.HashMap;
import java.util.Map;

public final class Position {

    private static final Map<Integer, Map<Integer, Position>> positions = new HashMap<>();

    public final int x;
    public final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
        if (!positions.containsKey(x))
            positions.put(x, new HashMap<>());
        positions.get(x).put(y, this);
    }

    public static Position of(int x, int y) {
        return positions.containsKey(x) && positions.get(x).containsKey(y) ?
                positions.get(x).get(y) : new Position(x, y);
    }

    public Position add(Position other) {
        return Position.of(this.x + other.x, this.y + other.y);
    }

    public Position subtract(Position other) {
        return Position.of(this.x - other.x, this.y - other.y);
    }

    public Direction shootDirection(Position playerPosition) {
        Position relativePosition = this.subtract(playerPosition);
        return null;
    }

}
