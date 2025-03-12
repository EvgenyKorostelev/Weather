package ru.korostelev.Weather.services;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korostelev.Weather.clients.OpenWeatherRestClient;
import ru.korostelev.Weather.clients.payload.CityCoordinatesRequest;
import ru.korostelev.Weather.clients.payload.CityCoordinatesResponse;
import ru.korostelev.Weather.entity.Coordinates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты WeatherService")
public class CoordinatesCityServiceImpTest {

    @Mock
    private OpenWeatherRestClient openWeatherRestClient;

    @InjectMocks
    CoordinatesServiceImp coordinatesService;


    @Test
    void findCoordinatesByNameSuccess(){

        String cityName = "Orlando";
        String userName = "Kira";
        var response = new CityCoordinatesResponse(
                "Orlando",
                28.540932,
                -81.377941,
                "USA",
                "Florida"
        );

        when(openWeatherRestClient.findCoordinatesByCityName(new CityCoordinatesRequest(cityName), userName))
                .thenReturn(response);

        assertEquals(coordinatesService.findCoordinatesByName(cityName, userName),
                new Coordinates(response.lat(), response.lon()));
    }

}
