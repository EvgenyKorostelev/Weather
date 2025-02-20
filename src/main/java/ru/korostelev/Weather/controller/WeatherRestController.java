package ru.korostelev.Weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.entity.Weather;
import ru.korostelev.Weather.services.CoordinatesCityService;
import ru.korostelev.Weather.services.WeatherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather/")
public class WeatherRestController {

    private final WeatherService weatherService;
    private final CoordinatesCityService coordinatesCityService;


    @GetMapping("{cityName:\\s+}")
    public City getWeather(@PathVariable("cityName") String cityName) {
        Coordinates coordinates = coordinatesCityService.getCoordinatesByName(cityName);
        return weatherService.getWeather(cityName, coordinates);
    }

}
