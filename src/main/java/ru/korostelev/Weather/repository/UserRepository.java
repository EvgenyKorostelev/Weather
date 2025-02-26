package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User getUserByName(String userName);

    List<User> getAllUsers();
}
