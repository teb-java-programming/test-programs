package com.teb.practice.jupiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.teb.practice.User;
import com.teb.practice.UserRepository;
import com.teb.practice.UserService;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

class UserServiceTest {

    private static final String ID = "U01";
    private static final String NAME = "John";
    private static final String SECRET = "secret";

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void testAddsUser() {

        User user = new User(ID, NAME, SECRET);

        userService.addUser(user);

        verify(userRepository).save(user);
    }

    @Test
    void testAddsAndRetrievesUser() {

        User user = new User(ID, NAME, SECRET);

        when(userRepository.findAll()).thenReturn(List.of(user));

        User result = userService.getUserById("U01");

        assertEquals(user, result);
        verify(userRepository).findAll();
    }

    @Test
    void testThrowsExceptionWhenUserNotFound() {

        when(userRepository.findAll()).thenReturn(List.of());

        Exception e =
                assertThrows(RuntimeException.class, () -> userService.getUserById("unknown"));
        assertInstanceOf(NoSuchElementException.class, e);
    }
}
