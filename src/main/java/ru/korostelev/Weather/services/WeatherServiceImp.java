package ru.korostelev.Weather.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.entity.Weather;
import ru.korostelev.Weather.repository.WeatherRepository;

@Service
@RequiredArgsConstructor
public class WeatherServiceImp implements WeatherService{

    private final WeatherRepository weatherRepository;

    @Override
    public City getWeather(String cityName, Coordinates coordinates) {

//        Weather weather = new Weather();
        City city = new City(cityName, coordinates, weather.getTemperature(), weather.getClouds(),
                weather.getVisibility(), weather.getWindSpeed(), weather.getDateTime());
                weatherRepository.save(city);
        return city;
    }
}
