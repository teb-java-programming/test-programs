package com.teb.practice.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.teb.practice.User;
import com.teb.practice.UserRepository;
import com.teb.practice.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

class UserServiceTest {

    private static final String ID = "U01";
    private static final String NAME = "John";
    private static final String SECRET = "secret";

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void testSavesUser() {

        User user = new User(ID, NAME, SECRET);

        userService.addUser(user);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        assertEquals(user, captor.getValue());
    }

    @Test
    void testGetsUserFromList() {

        User user = new User(ID, NAME, SECRET);

        when(userRepository.findAll()).thenReturn(List.of(user));

        User result = userService.getUserById("U01");

        assertEquals(user, result);
    }

    @Test
    void testGetUserNotFound() {

        when(userRepository.findAll()).thenReturn(List.of());

        Exception e =
                assertThrows(RuntimeException.class, () -> userService.getUserById("unknown"));
        assertInstanceOf(NoSuchElementException.class, e);
    }

    @Test
    void testRepositoryInjectedViaConstructor() throws Exception {

        Field field = UserService.class.getDeclaredField("repository");
        field.setAccessible(true);

        assertSame(userRepository, field.get(userService));
    }
}
