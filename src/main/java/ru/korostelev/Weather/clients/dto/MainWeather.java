package ru.korostelev.Weather.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MainWeather(@JsonProperty("temp") Float temp) {
}
