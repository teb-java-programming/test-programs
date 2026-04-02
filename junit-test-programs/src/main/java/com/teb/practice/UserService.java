package com.teb.practice;

import java.util.NoSuchElementException;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void addUser(User user) {

        repository.save(user);
    }

    public User getUserById(String id) {

        return repository.findAll().stream()
                .filter(user -> id.equals(user.id()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}