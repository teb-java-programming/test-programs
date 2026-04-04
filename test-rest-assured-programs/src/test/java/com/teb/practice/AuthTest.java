package com.teb.practice;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.teb.practice.config.RequestConfig.getRequestSpec;
import static com.teb.practice.config.ResponseConfig.serverError;
import static com.teb.practice.config.ResponseConfig.success;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;

import com.teb.practice.config.WireMockConfig;

import org.junit.jupiter.api.Test;

public class AuthTest extends WireMockConfig {

    @Test
    void testGetUser() {

        stubFor(
                get(urlEqualTo("/users/U01"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{\"id\": \"U01\", \"name\": \"Adam\"}")));

        String response =
                given().spec(getRequestSpec()).when().get("/users/U01").then().extract().asString();

        assertThat(response).contains("Adam");
    }

    @Test
    void testLogin() {

        stubFor(
                post(urlEqualTo("/login"))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{\"token\": \"tok1111\"}")));

        given().spec(getRequestSpec())
                .body("{\"email\": \"user@test.com\", \"password\": \"pass1234\"}")
                .when()
                .post("/login")
                .then()
                .spec(success());
    }

    @Test
    void testLoginFails() {

        stubFor(
                post(urlEqualTo("/login"))
                        .willReturn(
                                aResponse()
                                        .withStatus(500)
                                        .withHeader("Content-Type", "application/json")
                                        .withBody("{\"error\": \"Not found\"}")));

        given().spec(getRequestSpec())
                .body("{\"email\": \"user@test.com\", \"password\": \"pass1234\"}")
                .when()
                .post("/login")
                .then()
                .spec(serverError());
    }
}
