package ru.korostelev.Weather.repository;

import ru.korostelev.Weather.entity.City;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

    void save(City city);

    Optional<City> getCityByCityName(String cityName);

    List<City> getAllCities();
}
