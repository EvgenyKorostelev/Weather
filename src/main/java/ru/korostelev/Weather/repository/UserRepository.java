package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> getUserByName(String userName);

    List<User> getAllUsers();
}
