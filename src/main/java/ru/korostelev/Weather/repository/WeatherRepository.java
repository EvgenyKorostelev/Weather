package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.City;

public interface WeatherRepository {

    void save(City city);
}
