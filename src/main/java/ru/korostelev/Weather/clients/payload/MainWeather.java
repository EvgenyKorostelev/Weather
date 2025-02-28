package ru.korostelev.Weather.clients.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MainWeather(@JsonProperty("temp") Float temp) {
}
