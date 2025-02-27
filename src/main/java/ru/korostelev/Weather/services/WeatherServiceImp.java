package ru.korostelev.Weather.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.clients.OpenWeatherRestClient;
import ru.korostelev.Weather.clients.dto.CityWeatherRequest;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.repository.WeatherRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WeatherServiceImp implements WeatherService {

    private final OpenWeatherRestClient openWeatherRestClient;

    private final WeatherRepository weatherRepository;

    @Override
    public City getWeather(String cityName, Coordinates coordinates, String userName) {
        Optional<City> cacheCity = weatherRepository.getCityByCityName(cityName);
        if (cacheCity.isPresent()) {
            return cacheCity.get();
        } else {
            var response = openWeatherRestClient.getWeatherByCityCoordinates(new CityWeatherRequest(coordinates), userName);
            City city = new City(cityName, coordinates, response.mainWeather().temp(), response.clouds().all(),
                    response.visibility(), response.wind().speed(), response.datetime());
            weatherRepository.save(city);
            return city;
        }
    }
}
