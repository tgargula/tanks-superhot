package pl.edu.agh.cs.lab.tgargula.basics;

import javafx.geometry.Pos;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    public static Stream<Arguments> addData() {
        return Stream.of(
                Arguments.of(Position.of(1, 1), Position.of(0, 1), Position.of(1, 0)),
                Arguments.of(Position.of(21, 37), Position.of(42, -5), Position.of(-21, 42)),
                Arguments.of(Position.of(0, 0), Position.of(0, 0), Position.of(0, 0))
        );
    }

    public static Stream<Arguments> subtractData() {
        return Stream.of(
                Arguments.of(Position.of(0, 0), Position.of(0, 0), Position.of(0, 0)),
                Arguments.of(Position.of(1, 5), Position.of(10, 26), Position.of(9, 21)),
                Arguments.of(Position.of(2, 1), Position.of(4, -4), Position.of(2, -5))
        );
    }

    public static Stream<Arguments> getBestMoveDirectionData() {
        return Stream.of(
                Arguments.of(Direction.SOUTH, Position.of(5, 5), Position.of(5, 10)),
                Arguments.of(Direction.EAST, Position.of(4, 6), Position.of(10, 7)),
                Arguments.of(Direction.NORTH, Position.of(0,5), Position.of(4, 0))
        );
    }

    public static Stream<Arguments> getBestShootDirectionData() {
        return Stream.of(
                Arguments.of(Direction.SOUTH, Position.of(5, 5), Position.of(5, 10)),
                Arguments.of(Direction.NORTHEAST, Position.of(0,5), Position.of(4, 0)),
                Arguments.of(Direction.EAST, Position.of(0,0), Position.of(10,4))
        );
    }

    @ParameterizedTest
    @MethodSource("addData")
    public void addTest(Position expected, Position first, Position second) {
        assertEquals(expected, first.add(second));
    }

    @ParameterizedTest
    @MethodSource("subtractData")
    public void subtractTest(Position expected, Position first, Position second) {
        assertEquals(expected, first.subtract(second));
    }

    @ParameterizedTest
    @MethodSource("getBestMoveDirectionData")
    public void getBestMoveDirectionTest(Direction expected, Position position, Position playerPosition) {
        assertEquals(expected, position.getBestMoveDirection(playerPosition));
    }

    @ParameterizedTest
    @MethodSource("getBestShootDirectionData")
    public void getBestShootDirection(Direction expected, Position position, Position playerPosition) {
        assertEquals(expected, position.getBestShootDirection(playerPosition));
    }
}
