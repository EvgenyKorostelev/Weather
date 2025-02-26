package ru.korostelev.Weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.clients.Coordinates;
import ru.korostelev.Weather.services.CoordinatesCityService;
import ru.korostelev.Weather.services.WeatherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather/")
public class WeatherRestController {

    private final WeatherService weatherService;
    private final CoordinatesCityService coordinatesCityService;


    @GetMapping("{cityName:\\S+}")
    public City getWeather(@PathVariable("cityName") String cityName,
                           @RequestBody String userName) {
        Coordinates coordinates = coordinatesCityService.getCoordinatesByName(cityName, userName);
        return weatherService.getWeather(cityName, coordinates, userName);
    }

}
