package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.clients.Coordinates;

public interface WeatherService {

    City getWeather(String cityName, Coordinates coordinates, String userName);
}
