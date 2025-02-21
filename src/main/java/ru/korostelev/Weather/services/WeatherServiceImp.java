package ru.korostelev.Weather.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.clients.CityWeatherRequest;
import ru.korostelev.Weather.clients.OpenWeatherMapApiClient;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.repository.WeatherRepository;

@Service
@RequiredArgsConstructor
public class WeatherServiceImp implements WeatherService {

    private final OpenWeatherMapApiClient openWeatherMapApiClient;

    private final WeatherRepository weatherRepository;

    @Override
    public City getWeather(String cityName, Coordinates coordinates, String userName) {

        var response = openWeatherMapApiClient.getWeatherByCityCoordinates(new CityWeatherRequest(coordinates), userName);
        City city = new City(cityName, coordinates, response.weather().getTemperature(), response.weather().getClouds(),
                response.weather().getVisibility(), response.weather().getWindSpeed(), response.weather().getDateTime());
        weatherRepository.save(city);
        return city;
    }
}
