package com.teb.practice.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.teb.practice.Calculator;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private static final int INPUT_ONE = 72;
    private static final int INPUT_TWO = 9;

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {

        assertThat(calculator.add(INPUT_ONE, INPUT_TWO), is(81));
    }

    @Test
    void testDivide() {

        assertThat(calculator.divide(INPUT_ONE, INPUT_TWO), is(8.0));
    }

    @Test
    void testDivideThrowsException() {

        Exception e = assertThrows(RuntimeException.class, () -> calculator.divide(INPUT_ONE, 0));
        assertThat(e, instanceOf(ArithmeticException.class));
    }
}
