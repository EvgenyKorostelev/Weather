package ru.korostelev.Weather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UsersController {

    private final UserService userService;

    @PostMapping("/registration")
    public User registartionUser(@RequestBody String userName,
                                 @RequestBody String apiKey) {
        return userService.createUser(userName, apiKey);
    }

    @GetMapping("{userName:\\s+}")
    public User findUser(@PathVariable("username") String userName){
        return userService.findUserByName(userName);
    }
}
