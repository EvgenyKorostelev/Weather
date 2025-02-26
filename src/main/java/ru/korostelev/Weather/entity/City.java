package ru.korostelev.Weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.korostelev.Weather.clients.Coordinates;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class City {

    private String cityName;

    private Coordinates coordinates;

    private Float temperature;

    private Integer clouds;

    private Integer visibility;

    private Float windSpeed;

    private Integer dateTime;

}
