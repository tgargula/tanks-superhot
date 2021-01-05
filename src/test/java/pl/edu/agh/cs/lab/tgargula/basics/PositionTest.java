package pl.edu.agh.cs.lab.tgargula.basics;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    public static Stream<Arguments> add() {
        return Stream.of(
                Arguments.of(Position.of(1, 1), Position.of(0, 1), Position.of(1, 0)),
                Arguments.of(Position.of(21, 37), Position.of(42, -5), Position.of(-21, 42)),
                Arguments.of(Position.of(0, 0), Position.of(0, 0), Position.of(0, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("add")
    public void addTest(Position expected, Position first, Position second) {
        assertEquals(expected, first.add(second));
    }
}
