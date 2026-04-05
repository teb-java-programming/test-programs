package com.teb.practice.api;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class ApiClient {

    public boolean callApi(String input) {

        if ("null".equalsIgnoreCase(input)) {
            input = null;
        }

        if (input == null) {
            return false;
        }

        return isNumeric(input);
    }
}
