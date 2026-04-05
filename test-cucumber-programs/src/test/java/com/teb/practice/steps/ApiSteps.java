package com.teb.practice.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.lang.Boolean.parseBoolean;

import com.teb.practice.api.ApiClient;
import com.teb.practice.context.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApiSteps {

    private final TestContext context = new TestContext();
    private final ApiClient apiClient = new ApiClient();

    @Given("I send a request with {string}")
    public void iSendARequestWith(String input) {

        context.setInputString(input);
    }

    @When("I call the API")
    public void iCallTheApi() {

        context.setBooleanValue(apiClient.callApi(context.getInputString()));
    }

    @Then("the response should be {word}")
    public void theResponseShouldBe(String expected) {

        assertEquals(parseBoolean(expected), context.isBooleanValue());
    }
}
