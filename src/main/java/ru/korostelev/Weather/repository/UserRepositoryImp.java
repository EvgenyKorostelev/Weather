package ru.korostelev.Weather.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.korostelev.Weather.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Repository
public class UserRepositoryImp implements UserRepository {

    List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return users.stream()
                .filter(o -> o.getUserName().equals(userName))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}
