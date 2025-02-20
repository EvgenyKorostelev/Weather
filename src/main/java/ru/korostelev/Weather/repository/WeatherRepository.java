package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Weather;

public interface WeatherRepository {

    void save(City city);
}
