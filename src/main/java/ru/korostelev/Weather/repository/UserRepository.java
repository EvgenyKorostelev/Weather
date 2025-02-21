package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.User;

public interface UserRepository {

    void save(User user);

    User getUserByName(String userName);
}
