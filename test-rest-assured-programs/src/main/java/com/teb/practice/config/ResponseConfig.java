package com.teb.practice.config;

import static org.hamcrest.Matchers.lessThan;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseConfig {

    public static ResponseSpecification success() {

        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(5000L))
                .build();
    }

    public static ResponseSpecification serverError() {

        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .expectResponseTime(lessThan(5000L))
                .build();
    }
}
