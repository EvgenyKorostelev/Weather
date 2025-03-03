package ru.korostelev.Weather.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;


    @Override
    public Optional<User> createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public boolean deleteUserByName(String userName) {
        return userRepository.deleteUserByName(userName);
    }
}
