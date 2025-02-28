package ru.korostelev.Weather.clients.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CityCoordinatesResponse(@JsonProperty("name") String name,
                                      @JsonProperty("lat") Double lat,
                                      @JsonProperty("lon") Double lon,
                                      @JsonProperty("country") String country,
                                      @JsonProperty("state") String state) {
}
