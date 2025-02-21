package ru.korostelev.Weather.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.korostelev.Weather.entity.Coordinates;

public record CityCoordinatesResponse(@JsonProperty("coordinates") Coordinates coordinates) {
}
