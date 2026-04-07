package com.teb.practice.jupiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.Calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CalculatorTest {

    private static final int INPUT_ONE = 48;
    private static final int INPUT_TWO = 8;

    private final Calculator calculator = new Calculator();

    static Stream<Arguments> divisionProvider() {

        return Stream.of(
                Arguments.of(64, 16, 4.0, null),
                Arguments.of(-78, 6, -13.0, null),
                Arguments.of(92, -4, -23.0, null),
                Arguments.of(-88, -11, 8.0, null),
                Arguments.of(4, 0, null, ArithmeticException.class));
    }

    @Test
    void testAdd() {

        assertEquals(56, calculator.add(INPUT_ONE, INPUT_TWO));
    }

    @Test
    void testDivide() {

        assertEquals(6, calculator.divide(INPUT_ONE, INPUT_TWO));
    }

    @Test
    void testDivideThrowsException() {

        Exception e = assertThrows(RuntimeException.class, () -> calculator.divide(INPUT_ONE, 0));
        assertInstanceOf(ArithmeticException.class, e);
        assertTrue(e.getMessage().contains("divide by zero"));
    }

    @ParameterizedTest
    @CsvSource({"29, 15, 44", "-12, -61, -73", "0, 1, 1", "14, -52, -38"})
    void testAdd(int inputOne, int inputTwo, int result) {

        assertEquals(result, calculator.add(inputOne, inputTwo));
    }

    @ParameterizedTest
    @MethodSource("divisionProvider")
    void testDivide(
            int inputOne, int inputTwo, Double result, Class<? extends Throwable> exception) {

        if (exception == null) assertEquals(result, calculator.divide(inputOne, inputTwo));
        else assertThrows(exception, () -> calculator.divide(inputOne, inputTwo));
    }
}
