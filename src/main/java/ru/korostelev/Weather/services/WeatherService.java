package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;

import java.util.List;

public interface WeatherService {

    City findWeather(String cityName, Coordinates coordinates, String userName);

    List<City> findAllCacheCities();
}
