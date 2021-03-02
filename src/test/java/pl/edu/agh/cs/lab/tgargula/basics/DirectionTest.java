package pl.edu.agh.cs.lab.tgargula.basics;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {

    public static Stream<Arguments> getMoveDirectionData() {
        return Stream.of(
                Arguments.of(Direction.EAST, Position.of(1, 0)),
                Arguments.of(Direction.SOUTH, Position.of(0, 1)),
                Arguments.of(Direction.WEST, Position.of(-1, 0)),
                Arguments.of(Direction.NORTH, Position.of(0, -1)),
                Arguments.of(Direction.WEST, Position.of(-10, -9)),
                Arguments.of(Direction.NORTH, Position.of(15, -16))
        );
    }

    public static Stream<Arguments> getShootDirectionData() {
        return Stream.of(
                Arguments.of(Direction.EAST, 0),
                Arguments.of(Direction.SOUTHEAST, PI / 4),
                Arguments.of(Direction.SOUTH, PI / 2),
                Arguments.of(Direction.SOUTHWEST, 3 * PI / 4),
                Arguments.of(Direction.WEST, PI),
                Arguments.of(Direction.NORTHWEST, -3 * PI / 4),
                Arguments.of(Direction.NORTH, -PI / 2),
                Arguments.of(Direction.NORTHEAST, -PI / 4)
        );
    }

    public static Stream<Arguments> rotateLeftData() {
        return Stream.of(
                Arguments.of(Direction.WEST, Direction.NORTHWEST),
                Arguments.of(Direction.SOUTHEAST, Direction.SOUTH)
        );
    }

    public static Stream<Arguments> rotateRightData() {
        return Stream.of(
                Arguments.of(Direction.NORTHWEST, Direction.WEST),
                Arguments.of(Direction.EAST, Direction.NORTHEAST)
        );
    }

    @ParameterizedTest
    @MethodSource("getMoveDirectionData")
    public void getMoveDirectionTest(Direction expected, Position relativePosition) {
        assertEquals(expected, Direction.getMoveDirection(relativePosition));
    }

    @ParameterizedTest
    @MethodSource("getShootDirectionData")
    public void getShootDirectionTest(Direction expected, double angle) {
        assertEquals(expected, Direction.getShootDirection(angle));
    }

    @ParameterizedTest
    @MethodSource("rotateLeftData")
    public void rotateLeftTest(Direction expected, Direction initial) {
        assertEquals(expected, initial.rotateLeft());
    }

    @ParameterizedTest
    @MethodSource("rotateRightData")
    public void rotateRightTest(Direction expected, Direction initial) {
        assertEquals(expected, initial.rotateRight());
    }
}
