package com.teb.practice.config;

import static io.restassured.http.ContentType.JSON;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestConfig {

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8089)
                .setContentType(JSON)
                .build();
    }
}
