package com.teb.practice.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        assertThat(result).isEqualTo(user);
        verify(userRepository).findAll();
    }

    @Test
    void testThrowsExceptionWhenUserNotFound() {

        when(userRepository.findAll()).thenReturn(List.of());

        assertThatThrownBy(() -> userService.getUserById("unknown"))
                .isInstanceOf(NoSuchElementException.class);
    }
}
