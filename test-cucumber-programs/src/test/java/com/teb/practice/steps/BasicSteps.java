package com.teb.practice.steps;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.trim;
import static org.apache.commons.lang3.StringUtils.upperCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.lang.Boolean.parseBoolean;

import com.teb.practice.context.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BasicSteps {

    private final TestContext context = new TestContext();

    @Given("I have a string {string}")
    public void iHaveAString(String input) {

        if ("null".equalsIgnoreCase(input)) {
            input = null;
        }

        if (input == null) {
            context.setError(true);
            return;
        }

        context.setInputString(input);
        context.setError(false);
    }

    @When("I convert it to {string}")
    public void iConvertItToUppercase(String operation) {

        String result = "";

        if (context.getInputString() == null) {
            context.setError(true);

            return;
        }

        if (operation.equalsIgnoreCase("uppercase")) {
            result = upperCase(context.getInputString());
        } else if (operation.equalsIgnoreCase("lowercase")) {
            result = lowerCase(context.getInputString());
        } else if (operation.equalsIgnoreCase("capitalise")) {
            result = capitalize(context.getInputString());
        } else if (operation.equalsIgnoreCase("trim")) {
            result = trim(context.getInputString());
        }

        context.setStringResult(result);
    }

    @Then("the string should be {string}")
    public void theStringResultShouldBe(String expected) {

        assertEquals(expected, context.getStringResult(), "String result mismatch");
    }

    @Given("I have numbers {int} and {int}")
    public void iHaveNumbers(int numOne, int numTwo) {

        if (numOne < 0 || numTwo < 0) {
            throw new IllegalArgumentException("Negative numbers not allowed");
        }

        context.setFirstNumber(numOne);
        context.setSecondNumber(numTwo);
    }

    @When("I perform {string}")
    public void iPerformOperation(String operation) {

        int result = 0;

        if (operation.equalsIgnoreCase("add")) {
            result = context.getFirstNumber() + context.getSecondNumber();
        } else if (operation.equalsIgnoreCase("subtract")) {
            result = context.getFirstNumber() - context.getSecondNumber();
        } else if (operation.equalsIgnoreCase("multiply")) {
            result = context.getFirstNumber() * context.getSecondNumber();
        } else if (operation.equalsIgnoreCase("divide")) {
            if (context.getSecondNumber() == 0) {
                context.setNumericResult(0);
                context.setError(true);
            } else result = context.getFirstNumber() / context.getSecondNumber();
        }

        context.setNumericResult(result);
    }

    @Then("the numeric result should be {int}")
    public void theNumericResultShouldBe(Integer expected) {

        assertEquals(expected, context.getNumericResult(), "Numeric result mismatch");
    }

    @Then("an error should occur")
    public void anErrorShouldOccur() {

        assertTrue(context.isError(), "Expected an error but none occurred");
    }

    @Given("I have a boolean value {word}")
    public void iHaveABooleanValue(String value) {

        context.setBooleanValue(parseBoolean(value));
    }

    @Then("the boolean should be true")
    public void theBooleanShouldBeTrue() {

        assertTrue(context.isBooleanValue());
    }

    @Then("the boolean should be false")
    public void theBooleanShouldBeFalse() {

        assertFalse(context.isBooleanValue());
    }
}
