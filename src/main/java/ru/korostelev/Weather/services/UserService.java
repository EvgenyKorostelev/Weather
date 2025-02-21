package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.User;

public interface UserService {

    User createUser(String userName, String apiKey);

    User findUserByName(String userName);
}
