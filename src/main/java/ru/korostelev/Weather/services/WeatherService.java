package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.entity.Weather;

public interface WeatherService {

    City getWeather(String sityname, Coordinates coordinates);
}
