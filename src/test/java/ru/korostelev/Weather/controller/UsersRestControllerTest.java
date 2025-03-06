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

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
}
