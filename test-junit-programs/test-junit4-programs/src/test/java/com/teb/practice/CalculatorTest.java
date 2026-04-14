package com.teb.practice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static java.util.Arrays.asList;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

public class CalculatorTest {

    private static final int INPUT_ONE = 48;
    private static final int INPUT_TWO = 8;

    private final Calculator calculator = new Calculator();

    @Parameters
    public static Collection<Object[]> data() {

        return asList(
                new Object[][] {
                    {64, 16, 4.0, null},
                    {-78, 6, -13.0, null},
                    {92, -4, -23.0, null},
                    {-88, -11, 8.0, null},
                    {4, 0, null, ArithmeticException.class}
                });
    }

    @Test
    public void testAdd() {

        assertEquals(56, calculator.add(INPUT_ONE, INPUT_TWO));
    }

    @Test
    public void testDivide() {

        assertEquals(6, calculator.divide(INPUT_ONE, INPUT_TWO), 0.0);
    }

    @Test
    public void testDivideThrowsException() {

        try {
            calculator.divide(INPUT_ONE, 0);
        } catch (Exception e) {
            assertTrue(e instanceof ArithmeticException);
            assertTrue(e.getMessage().contains("divide by zero"));

            return;
        }

        throw new AssertionError("Expected exception was not thrown");
    }
}
