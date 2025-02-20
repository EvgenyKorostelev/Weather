package ru.korostelev.Weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class City {

    private final String cityName;

    private final Coordinates coordinates;

    private Float temperature;

    private String clouds;

    private Integer visibility;

    private Float windSpeed;

    private String dateTime;

}
