package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> findUserByName(String userName);

    List<User> findAllUsers();
}
