package ru.korostelev.Weather.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Wind(@JsonProperty("speed") Float speed) {
}
