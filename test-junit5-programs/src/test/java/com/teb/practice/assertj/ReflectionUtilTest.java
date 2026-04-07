package com.teb.practice.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        assertThat(result).isEqualTo(SECRET);
    }

    @Test
    void testThrowsExceptionWhenFieldDoesNotExist() {

        User user = new User(ID, NAME, SECRET);

        assertThatThrownBy(() -> reflectionUtil.getPrivateField(user, "unknown"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Error accessing field");
    }

    @Test
    void testInvokesMethod() {

        Object result = reflectionUtil.invokeMethod(INPUT, "toUpperCase");

        assertThat(result).isEqualTo("HELLO-TEST");
    }

    @Test
    void testThrowsExceptionWhenMethodDoesNotExist() {

        assertThatThrownBy(() -> reflectionUtil.invokeMethod(INPUT, "unknownMethod"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Error invoking method");
    }
}
