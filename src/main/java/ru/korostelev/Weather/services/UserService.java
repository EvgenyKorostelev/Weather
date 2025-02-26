package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User findUserByName(String userName);

    List<User> findAllUsers();
}
