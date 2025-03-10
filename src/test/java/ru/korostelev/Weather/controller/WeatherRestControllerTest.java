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
import ru.korostelev.Weather.controller.payload.ReceiveWeatherUserPayload;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.services.CoordinatesService;
import ru.korostelev.Weather.services.WeatherService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты UsersRestController")
public class WeatherRestControllerTest {

    @Mock
    private WeatherService weatherService;

    @Mock
    private CoordinatesService coordinatesService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private WeatherRestController weatherRestController;


    @Test
    void cityWeatherSuccess() throws BindException {

        String cityName = "Orlando";
        Coordinates coordinatesOrlando = new Coordinates(28.540932, -81.377941);
        City Orlando = new City("Orlando", coordinatesOrlando,
                14.5f,52, 10000, 5.8f, 1741585297);
        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Alex");

        when(bindingResult.hasErrors()).thenReturn(false);
        when(coordinatesService.findCoordinatesByName(cityName, payload.userName())).thenReturn(coordinatesOrlando);
        when(weatherService.findWeather(cityName, coordinatesOrlando, payload.userName())).thenReturn(Orlando);

        Coordinates coordinates = coordinatesService.findCoordinatesByName(cityName, payload.userName());
        City city = weatherService.findWeather(cityName, coordinates, payload.userName());
        ResponseEntity<?> response = ResponseEntity.ok(city);

        assertEquals(weatherRestController.cityWeather(cityName, payload, bindingResult), response);
    }

    @Test
    void cityWeatherBindExceptionErrors() {

        String cityName = "";
        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Alex");

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult instanceof BindException).thenReturn(true);

        assertThrows(BindException.class, () ->
                weatherRestController.cityWeather(cityName ,payload, bindingResult));
        verify(bindingResult).hasErrors();
        verifyNoInteractions(coordinatesService);
        verifyNoInteractions(weatherService);
    }

    @Test
    void cityWeatherErrorsForBindException() {

        String cityName = "Orlando";
        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Alex");

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult instanceof BindException).thenReturn(false);
        BindException exception = new BindException(bindingResult);

        BindException thrown = assertThrows(BindException.class, () ->
                weatherRestController.cityWeather(cityName ,payload, bindingResult));
        assertEquals(exception.getMessage(), thrown.getMessage());
        verify(bindingResult).hasErrors();
        verifyNoInteractions(coordinatesService);
        verifyNoInteractions(weatherService);
    }
}
