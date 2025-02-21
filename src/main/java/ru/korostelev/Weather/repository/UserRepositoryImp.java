package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImp implements UserRepository {

    Map<String, String> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserName(), user.getApiKey());
    }

    @Override
    public User getUserByName(String userName) {
        return new User(userName, users.get(userName));
    }
}
