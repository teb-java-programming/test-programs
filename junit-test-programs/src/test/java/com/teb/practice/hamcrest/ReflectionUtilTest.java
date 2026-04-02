package com.teb.practice.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertThat(result, is(SECRET));
    }

    @Test
    void testThrowsExceptionWhenFieldDoesNotExist() {

        User user = new User(ID, NAME, SECRET);

        Exception exception =
                assertThrows(
                        RuntimeException.class,
                        () -> reflectionUtil.getPrivateField(user, "unknown"));
        assertThat(exception.getMessage(), containsString("Error accessing field"));
    }

    @Test
    void testInvokesMethod() {

        Object result = reflectionUtil.invokeMethod(INPUT, "toUpperCase");

        assertThat(result, is("HELLO-TEST"));
    }

    @Test
    void testThrowsExceptionWhenMethodDoesNotExist() {

        Exception exception =
                assertThrows(
                        RuntimeException.class,
                        () -> reflectionUtil.invokeMethod(INPUT, "unknownMethod"));
        assertThat(exception.getMessage(), containsString("Error invoking method"));
    }
}
