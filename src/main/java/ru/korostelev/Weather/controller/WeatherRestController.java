package ru.korostelev.Weather.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.korostelev.Weather.controller.payload.ReceiveWeatherUserPayload;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Coordinates;
import ru.korostelev.Weather.services.CoordinatesCityService;
import ru.korostelev.Weather.services.WeatherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherRestController {

    private final WeatherService weatherService;
    private final CoordinatesCityService coordinatesCityService;


    @GetMapping("/{cityName:\\S+}")
    public ResponseEntity<?> getWeather(@PathVariable("cityName") String cityName,
                                        @Valid @RequestBody ReceiveWeatherUserPayload payload,
                                        BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Coordinates coordinates = coordinatesCityService.getCoordinatesByName(cityName, payload.userName());
            City city = weatherService.getWeather(cityName, coordinates, payload.userName());
            return ResponseEntity.ok(city);
        }
    }

}
