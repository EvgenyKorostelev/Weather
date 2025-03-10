package ru.korostelev.Weather.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.Weather.controller.payload.ReceiveWeatherUserPayload;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.services.CoordinatesService;
import ru.korostelev.Weather.services.WeatherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherRestController {

    private final WeatherService weatherService;

    private final CoordinatesService coordinatesService;


    @GetMapping("/{cityName:\\S+}")
    public ResponseEntity<?> cityWeather(@PathVariable("cityName") String cityName,
                                         @Valid @RequestBody ReceiveWeatherUserPayload payload,
                                         BindingResult bindingResult)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Coordinates coordinates = coordinatesService.findCoordinatesByName(cityName, payload.userName());
            City city = weatherService.findWeather(cityName, coordinates, payload.userName());
            return ResponseEntity.ok(city);
        }
    }

    @GetMapping("/update")
    public ResponseEntity<?> allCitiesWeather(@Valid @RequestBody ReceiveWeatherUserPayload payload,
                                              BindingResult bindingResult)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            List<City> cities = weatherService.findAllCacheCities();
            if (cities.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                for (City city : cities) {
                    Coordinates coordinates = coordinatesService.findCoordinatesByName(
                            city.getCityName(), payload.userName());
                    weatherService.findWeather(city.getCityName(), coordinates, payload.userName());
                }
                cities = weatherService.findAllCacheCities();
                return ResponseEntity.ok(cities);
            }

        }

    }

}
