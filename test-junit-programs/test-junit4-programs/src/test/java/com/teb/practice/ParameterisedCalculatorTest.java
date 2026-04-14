package com.teb.practice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static java.util.Arrays.asList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterisedCalculatorTest {

    private final Calculator calculator = new Calculator();

    private final int inputOne;
    private final int inputTwo;
    private final Double result;
    private final Class<? extends Throwable> exception;

    public ParameterisedCalculatorTest(
            int inputOne, int inputTwo, Double result, Class<? extends Throwable> exception) {
        this.inputOne = inputOne;
        this.inputTwo = inputTwo;
        this.result = result;
        this.exception = exception;
    }

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
    public void testDivide() {

        if (exception == null) {
            assertEquals(result, calculator.divide(inputOne, inputTwo), 0.0);
        } else {
            try {
                calculator.divide(inputOne, inputTwo);

                throw new AssertionError("Expected exception was not thrown");
            } catch (Exception e) {
                assertTrue(exception.isInstance(e));
            }
        }
    }
}
