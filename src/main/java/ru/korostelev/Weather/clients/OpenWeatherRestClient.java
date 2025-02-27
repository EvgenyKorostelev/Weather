package ru.korostelev.Weather.clients;

import ru.korostelev.Weather.clients.dto.CityCoordinatesRequest;
import ru.korostelev.Weather.clients.dto.CityCoordinatesResponse;
import ru.korostelev.Weather.clients.dto.CityWeatherRequest;
import ru.korostelev.Weather.clients.dto.CityWeatherResponse;

public interface OpenWeatherRestClient {

    CityCoordinatesResponse getCoordinatesByCityName(CityCoordinatesRequest request, String userName);

    CityWeatherResponse getWeatherByCityCoordinates(CityWeatherRequest request, String userName);
}
