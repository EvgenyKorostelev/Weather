package ru.korostelev.Weather.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;


    @Override
    public User createUser(String userName, String apiKey) {
        User user = new User(userName, apiKey);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }
}
