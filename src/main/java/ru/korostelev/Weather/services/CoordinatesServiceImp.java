package ru.korostelev.Weather.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.clients.OpenWeatherRestClient;
import ru.korostelev.Weather.clients.payload.CityCoordinatesRequest;


@Service
@AllArgsConstructor
public class CoordinatesServiceImp implements CoordinatesService {

    private final OpenWeatherRestClient openWeatherRestClient;

    @Override
    public Coordinates findCoordinatesByName(String cityName, String userName) {
        var response = openWeatherRestClient.getCoordinatesByCityName(new CityCoordinatesRequest(cityName), userName);
        return new Coordinates(response.lat(), response.lon());
    }
}
