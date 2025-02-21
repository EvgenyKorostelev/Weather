package ru.korostelev.Weather.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.korostelev.Weather.entity.Weather;

public record CityWeatherResponse(@JsonProperty("weather") Weather weather) {
}
