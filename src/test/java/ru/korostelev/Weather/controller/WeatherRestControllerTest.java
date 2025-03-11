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

import java.util.ArrayList;
import java.util.List;

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
                14.5f, 52, 10000, 5.8f, 1741585297);
        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Alex");

        when(bindingResult.hasErrors()).thenReturn(false);
        when(coordinatesService.findCoordinatesByName(cityName, payload.userName())).thenReturn(coordinatesOrlando);
        when(weatherService.findWeatherAndSaveToCache(cityName, coordinatesOrlando, payload.userName())).thenReturn(Orlando);

        Coordinates coordinates = coordinatesService.findCoordinatesByName(cityName, payload.userName());
        City city = weatherService.findWeatherAndSaveToCache(cityName, coordinates, payload.userName());
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
                weatherRestController.cityWeather(cityName, payload, bindingResult));
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
                weatherRestController.cityWeather(cityName, payload, bindingResult));
        assertEquals(exception.getMessage(), thrown.getMessage());
        verify(bindingResult).hasErrors();
        verifyNoInteractions(coordinatesService);
        verifyNoInteractions(weatherService);
    }

    @Test
    void allCitiesWeatherSuccess() throws BindException {

        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Odin");
        Coordinates coordinatesOrlando = new Coordinates(28.540932, -81.377941);
        Coordinates coordinatesParis = new Coordinates(48.856663, 2.351556);
        List<City> cities = new ArrayList<>(List.of(
                new City("Orlando", coordinatesOrlando,
                        14.5f, 52, 10000, 5.8f, 1741585297),
                new City("Paris", coordinatesParis,
                        16.7f, 34, 9000, 3.1f, 1741623995)
        ));
        List<City> responseCities = new ArrayList<>(List.of(
                new City("Orlando", coordinatesOrlando,
                        14.5f, 52, 10000, 5.8f, 1741585297 + 10),
                new City("Paris", coordinatesParis,
                        16.7f, 34, 9000, 3.1f, 1741623995 + 10)
        ));


        when(bindingResult.hasErrors()).thenReturn(false);
        when(weatherService.findAllCacheCities()).thenReturn(cities);
//        when(cities.isEmpty()).thenReturn(false);
        when(weatherService.findWeatherAndSaveToCache(
                cities.getFirst().getCityName(), cities.getFirst().getCoordinates(), payload.userName()))
                .thenReturn(responseCities.getFirst());
        when(weatherService.findWeatherAndSaveToCache(
                cities.getLast().getCityName(), cities.getLast().getCoordinates(), payload.userName()))
                .thenReturn(responseCities.getLast());

        ResponseEntity<?> response = ResponseEntity.ok(responseCities);

        assertEquals(weatherRestController.allCitiesWeather(payload, bindingResult), response);
    }

    @Test
    void allCitiesWeatherBindExceptionErrors() {

        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Odin");

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult instanceof BindException).thenReturn(true);

        assertThrows(BindException.class, () ->
                weatherRestController.allCitiesWeather(payload, bindingResult));
        verify(bindingResult).hasErrors();
        verifyNoInteractions(weatherService);
    }

    @Test
    void allCitiesWeatherErrorsForBindException() {

        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Odin");

        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult instanceof BindException).thenReturn(false);
        BindException exception = new BindException(bindingResult);

        BindException thrown = assertThrows(BindException.class, () ->
                weatherRestController.allCitiesWeather(payload, bindingResult));
        assertEquals(exception.getMessage(), thrown.getMessage());
        verify(bindingResult).hasErrors();
        verifyNoInteractions(weatherService);
    }

    @Test
    void allCitiesWeatherEmptyCache() throws BindException {

        ReceiveWeatherUserPayload payload = new ReceiveWeatherUserPayload("Odin");
        List<City> cities = new ArrayList<>();

        when(bindingResult.hasErrors()).thenReturn(false);
//        when(bindingResult instanceof BindException).thenReturn(false);
        when(weatherService.findAllCacheCities()).thenReturn(cities);
//        when(cities.isEmpty()).thenReturn(true);

        ResponseEntity<?> response = ResponseEntity.notFound().build();

        assertEquals(weatherRestController.allCitiesWeather(payload, bindingResult), response);
    }

}
