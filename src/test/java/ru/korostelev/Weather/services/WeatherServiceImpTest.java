package ru.korostelev.Weather.services;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korostelev.Weather.clients.OpenWeatherRestClient;
import ru.korostelev.Weather.clients.payload.*;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.repository.WeatherRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты WeatherService")
public class WeatherServiceImpTest {


    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private OpenWeatherRestClient openWeatherRestClient;

    @InjectMocks
    private WeatherServiceImp weatherService;


    @Test
    void findWeatherAndSaveToCacheFindCachedData() {

        String cityName = "Orlando";
        Coordinates coordinatesOrlando = new Coordinates(28.540932, -81.377941);
        String userName = "Elionora";
        Optional<City> cacheCity = Optional.of(new City("Orlando", coordinatesOrlando,
                14.5f, 52, 10000, 5.8f, 1741585297));

        when(weatherRepository.getCityByCityName(cityName)).thenReturn(cacheCity);

        City expected = cacheCity.get();

        assertEquals(weatherService.findWeatherAndSaveToCache(cityName, coordinatesOrlando, userName), expected);
    }

    @Test
    void findWeatherAndSaveToCacheFindApiData() {

        String cityName = "Orlando";
        Coordinates coordinatesOrlando = new Coordinates(28.540932, -81.377941);
        String userName = "Elionora";
        var response = new CityWeatherResponse(
                28.540932,
                -81.377941,
                new MainWeather(5.23f),
                10000,
                new Wind(23.34f),
                new Clouds(54),
                1741693918,
                "Dodo");

        when(weatherRepository.getCityByCityName(cityName)).thenReturn(Optional.empty());
        when(openWeatherRestClient.findWeatherByCityCoordinates(
                new CityWeatherRequest(coordinatesOrlando), userName)).thenReturn(response);

        City city = new City(cityName, coordinatesOrlando, response.mainWeather().temp(), response.clouds().all(),
                response.visibility(), response.wind().speed(), response.datetime());

        assertEquals(weatherService.findWeatherAndSaveToCache(cityName, coordinatesOrlando, userName), city);
        verify(weatherRepository).save(city);
    }
}
