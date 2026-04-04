package com.teb.practice;

public class Calculator {

    public int add(int inputOne, int inputTwo) {

        return inputOne + inputTwo;
    }

    public double divide(int inputOne, int inputTwo) {

        if (inputTwo == 0) throw new ArithmeticException("Cannot divide by zero");

        return (double) inputOne / inputTwo;
    }
}
