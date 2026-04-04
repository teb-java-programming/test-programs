package com.teb.practice.config;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

import com.github.tomakehurst.wiremock.WireMockServer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class WireMockConfig {

    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void setup() {

        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();

        configureFor("localhost", 8089);
    }

    @AfterAll
    static void tearDown() {

        wireMockServer.stop();
    }
}
