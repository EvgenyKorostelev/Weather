package ru.korostelev.Weather.clients.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Clouds(@JsonProperty("all") Integer all) {
}
