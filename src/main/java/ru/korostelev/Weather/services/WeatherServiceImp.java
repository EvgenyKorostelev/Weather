package ru.korostelev.Weather.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.clients.dto.CityWeatherRequest;
import ru.korostelev.Weather.clients.OpenWeatherMapApiClient;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.clients.Coordinates;
import ru.korostelev.Weather.repository.WeatherRepository;

@Service
@AllArgsConstructor
public class WeatherServiceImp implements WeatherService {

    private final OpenWeatherMapApiClient openWeatherMapApiClient;

    private final WeatherRepository weatherRepository;

    @Override
    public City getWeather(String cityName, Coordinates coordinates, String userName) {

        var response = openWeatherMapApiClient.getWeatherByCityCoordinates(new CityWeatherRequest(coordinates), userName);
        City city = new City(cityName, coordinates, response.mainWeather().temp(), response.clouds().all(),
                response.visibility(), response.wind().speed(), response.datetime());
        weatherRepository.save(city);
        return city;
    }
}
