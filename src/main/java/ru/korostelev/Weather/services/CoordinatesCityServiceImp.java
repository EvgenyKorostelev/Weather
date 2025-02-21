package ru.korostelev.Weather.services;

import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.clients.CityCoordinatesRequest;
import ru.korostelev.Weather.clients.OpenWeatherMapApiClient;
import ru.korostelev.Weather.entity.Coordinates;


@Service
@AllArgsConstructor
public class CoordinatesCityServiceImp implements CoordinatesCityService{

    private final OpenWeatherMapApiClient openWeatherMapApiClient;

    @Override
    public Coordinates getCoordinatesByName(String cityName, String userName) {
        var response = openWeatherMapApiClient.getCoordinatesByCityName(new CityCoordinatesRequest(cityName), userName);
        return response.coordinates();
    }
}
