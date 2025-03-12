package ru.korostelev.Weather.clients;

import ru.korostelev.Weather.clients.payload.CityCoordinatesRequest;
import ru.korostelev.Weather.clients.payload.CityCoordinatesResponse;
import ru.korostelev.Weather.clients.payload.CityWeatherRequest;
import ru.korostelev.Weather.clients.payload.CityWeatherResponse;

public interface OpenWeatherRestClient {

    CityCoordinatesResponse findCoordinatesByCityName(CityCoordinatesRequest request, String userName);

    CityWeatherResponse findWeatherByCityCoordinates(CityWeatherRequest request, String userName);
}
