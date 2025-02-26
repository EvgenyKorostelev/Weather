package ru.korostelev.Weather.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;


    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.getAllUsers();
    }
}
