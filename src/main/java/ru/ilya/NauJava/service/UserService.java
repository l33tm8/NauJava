package ru.ilya.NauJava.service;

import ru.ilya.NauJava.exception.UserAlreadyExistsException;
import ru.ilya.NauJava.model.User;

import java.util.List;

public interface UserService {
    void save(User user) throws UserAlreadyExistsException;

    User findById(Long id);

    void deleteById(Long id);

    void update(User user);

    List<User> findAll();
}
