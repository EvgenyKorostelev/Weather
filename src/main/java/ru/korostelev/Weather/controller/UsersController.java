package ru.korostelev.Weather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UsersController {

    private final UserService userService;

    @PostMapping("/registration")
    public User registartionUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("{userName:\\S+}")
    public User findUser(@PathVariable("userName") String userName){
        return userService.findUserByName(userName);
    }

    @GetMapping("users")
    public List<User> findAllRegisteredUsers(){
        return userService.findAllUsers();
    }
}
