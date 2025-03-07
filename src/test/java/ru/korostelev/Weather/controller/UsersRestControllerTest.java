package ru.korostelev.Weather.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;
import ru.korostelev.Weather.clients.exceptions.AlreadyExistException;
import ru.korostelev.Weather.controller.payload.NewUserPayload;
import ru.korostelev.Weather.entity.User;
import ru.korostelev.Weather.services.UserService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты UsersRestController")
public class UsersRestControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @InjectMocks
    private UsersRestController usersRestController;

    @Test
    void registrationUserSuccess() throws BindException, AlreadyExistException {
        NewUserPayload payload = new NewUserPayload("Odin", "a2da3da_KEY_3s65wr3fs7");
        Optional<User> user = Optional.of(new User(payload.userName(), payload.apiKey()));
        uriComponentsBuilder = UriComponentsBuilder.newInstance();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.createUser(new User(payload.userName(), payload.apiKey()))).thenReturn(user);

        ResponseEntity<?> response = usersRestController.registrationUser(
                payload, bindingResult, uriComponentsBuilder);

        assertEquals(ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("/api/user/{userName}")
                        .build(Map.of("userName", user.get().getUserName())))
                .body(user), response);
    }

    @Test
    void registrationUserBindExceptionErrors() {
        NewUserPayload payload = new NewUserPayload("O", "a2da3da_KEY_3s65wr3fs7");
        uriComponentsBuilder = UriComponentsBuilder.newInstance();


        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult instanceof BindException).thenReturn(true);

        assertThrows(BindException.class, () ->
                usersRestController.registrationUser(payload, bindingResult, uriComponentsBuilder));
        verify(bindingResult).hasErrors();
        verifyNoInteractions(userService);
    }

    @Test
    void registrationUserResultErrorsForBindException() {
        NewUserPayload payload = new NewUserPayload("", "");
        uriComponentsBuilder = UriComponentsBuilder.newInstance();

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult instanceof BindException).thenReturn(false);
        BindException exception = new BindException(bindingResult);

        BindException thrown = assertThrows(BindException.class, () ->
                usersRestController.registrationUser(payload, bindingResult, uriComponentsBuilder));
        assertEquals(exception.getMessage(), thrown.getMessage());
        verify(bindingResult).hasErrors();
        verifyNoInteractions(userService);
    }

    @Test
    void registrationUserAlreadyExistExceptionErrors() {
        NewUserPayload payload = new NewUserPayload("Odin", "a2da3da_KEY_3s65wr3fs7");
        Optional<User> user = Optional.empty();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(this.userService.createUser(new User(payload.userName(), payload.apiKey()))).thenReturn(user);
        AlreadyExistException exception = new AlreadyExistException("weather.errors.user.already_registered");

        AlreadyExistException thrown = assertThrows(AlreadyExistException.class, () ->
                usersRestController.registrationUser(payload, bindingResult, uriComponentsBuilder));
        assertEquals(exception.getMessage(), thrown.getMessage());
    }

    @Test
    void findUserSuccess(){
        String userName = "Odin";
        User user = new User(userName, "3sr6er2sy43er2fe5r24353q4e34234");

        when(userService.findUserByName(userName)).thenReturn(Optional.of(user));

        assertEquals(usersRestController.findUser(userName), user);
    }

    @Test
    void findUserNotFoundError(){
        String userName = "Odin";

        when(userService.findUserByName(userName)).thenReturn(Optional.empty());
        RuntimeException exception = new NoSuchElementException("weather.errors.user.not_found");

        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () ->
                usersRestController.findUser(userName));
        assertEquals(exception.getMessage(), thrown.getMessage());
    }

    @Test
    void findAllRegisteredUsers(){
        List<User> users = new ArrayList<>(List.of(
                new User("Odin", "wew2e232"),
                new User("Loki", "ww2ewe6w2e232")
        ));

        when(userService.findAllUsers()).thenReturn(users);

        assertEquals(usersRestController.findAllRegisteredUsers(), users);
    }

    @Test
    void deleteUserSuccess(){
        String userName = "Odin";

        when(userService.deleteUserByName(userName)).thenReturn(true);

        ResponseEntity<?> response = ResponseEntity.accepted().build();

        assertEquals(usersRestController.deleteUser(userName), response);
    }

    @Test
    void deleteUserUserNotFound(){
        String userName = "Odin";

        when(userService.deleteUserByName(userName)).thenReturn(false);

        ResponseEntity<?> response = ResponseEntity.notFound().build();

        assertEquals(usersRestController.deleteUser(userName), response);
    }
}
