package com.teb.practice.jupiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.User;
import com.teb.practice.util.ReflectionUtil;

import org.junit.jupiter.api.Test;

class ReflectionUtilTest {

    private static final String ID = "U01";
    private static final String NAME = "John";
    private static final String SECRET = "secret";
    private static final String INPUT = "hello-test";

    private final ReflectionUtil reflectionUtil = new ReflectionUtil();

    @Test
    void testGetsPrivateField() {

        User user = new User(ID, NAME, SECRET);

        String result = (String) reflectionUtil.getPrivateField(user, "secret");

        assertEquals(SECRET, result);
    }

    @Test
    void testThrowsExceptionWhenFieldDoesNotExist() {

        User user = new User(ID, NAME, SECRET);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> reflectionUtil.getPrivateField(user, "unknown"));
        assertTrue(exception.getMessage().contains("Error accessing field"));
    }

    @Test
    void testInvokeMethod() {

        Object result = reflectionUtil.invokeMethod(INPUT, "toUpperCase");

        assertEquals("HELLO-TEST", result);
    }

    @Test
    void testThrowsExceptionWhenMethodDoesNotExist() {

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> reflectionUtil.invokeMethod(INPUT, "unknownMethod"));
        assertTrue(exception.getMessage().contains("Error invoking method"));
    }
}
