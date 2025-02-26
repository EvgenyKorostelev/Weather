package ru.korostelev.Weather.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.clients.dto.CityCoordinatesRequest;
import ru.korostelev.Weather.clients.OpenWeatherMapApiClient;
import ru.korostelev.Weather.clients.Coordinates;


@Service
@AllArgsConstructor
public class CoordinatesCityServiceImp implements CoordinatesCityService {

    private final OpenWeatherMapApiClient openWeatherMapApiClient;

    @Override
    public Coordinates getCoordinatesByName(String cityName, String userName) {
        var response = openWeatherMapApiClient.getCoordinatesByCityName(new CityCoordinatesRequest(cityName), userName);
        return new Coordinates(response.lat(), response.lon());
    }
}
