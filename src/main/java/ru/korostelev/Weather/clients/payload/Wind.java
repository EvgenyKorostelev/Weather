package ru.korostelev.Weather.clients.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Wind(@JsonProperty("speed") Float speed) {
}
