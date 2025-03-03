package ru.korostelev.Weather.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.korostelev.Weather.clients.exceptions.AlreadyExistException;
import ru.korostelev.Weather.controller.payload.NewUserPayload;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UsersRestController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registrationUser(@Valid @RequestBody NewUserPayload payload,
                                              BindingResult bindingResult,
                                              UriComponentsBuilder uriComponentsBuilder)
            throws BindException, AlreadyExistException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<User> user = this.userService.createUser(new User(payload.userName(), payload.apiKey()));
            if (user.isPresent()) {
                return ResponseEntity
                        .created(uriComponentsBuilder
                                .replacePath("/api/user/{userName}")
                                .build(Map.of("userName", user.get().getUserName())))
                        .body(user);
            } else {
                throw new AlreadyExistException("weather.errors.user.already_registered");
            }
        }
    }

    @GetMapping("/{userName:\\S+}")
    public User findUser(@PathVariable("userName") String userName) {
        return this.userService.findUserByName(userName).orElseThrow(
                () -> new NoSuchElementException("weather.errors.user.not_found"));
    }

    @GetMapping("/users")
    public List<User> findAllRegisteredUsers() {
        return this.userService.findAllUsers();
    }

    @DeleteMapping("/{userName:\\S+}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName) {
        if (userService.deleteUserByName(userName)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
