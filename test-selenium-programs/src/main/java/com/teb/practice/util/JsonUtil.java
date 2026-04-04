package com.teb.practice.util;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonUtil {

    private static final JsonNode JSON_NODE;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonUtil.class.getClassLoader().getResourceAsStream("test/login.json");
            JSON_NODE = mapper.readTree(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    public static String getNestedValue(String parent, String child) {

        return JSON_NODE.get(parent).get(child).asString();
    }
}
