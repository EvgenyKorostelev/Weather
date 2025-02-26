package ru.korostelev.Weather.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CityWeatherResponse(@JsonProperty("coord.lat") Double lat,
                                  @JsonProperty("coord.lon") Double lon,
                                  @JsonProperty("main") MainWeather mainWeather,
                                  @JsonProperty("visibility") Integer visibility,
                                  @JsonProperty("wind") Wind wind,
                                  @JsonProperty("clouds") Clouds clouds,
                                  @JsonProperty("dt") Integer datetime,
                                  @JsonProperty("timezone") String timezone
) {
}
