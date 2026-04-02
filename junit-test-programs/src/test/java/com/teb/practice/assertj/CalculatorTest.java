package com.teb.practice.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.teb.practice.Calculator;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    private static final int INPUT_ONE = 96;
    private static final int INPUT_TWO = 12;

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {

        assertThat(calculator.add(INPUT_ONE, INPUT_TWO)).isEqualTo(108);
    }

    @Test
    void testDivide() {

        assertThat(calculator.divide(INPUT_ONE, INPUT_TWO)).isEqualTo(8);
    }

    @Test
    void testDivideThrowsException() {

        assertThatThrownBy(() -> calculator.divide(INPUT_ONE, 0))
                .isInstanceOf(ArithmeticException.class);
    }
}
