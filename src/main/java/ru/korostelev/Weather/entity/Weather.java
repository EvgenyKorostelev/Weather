package ru.korostelev.Weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {

    private Float temperature;

    private String clouds;

    private Integer visibility;

    private Float windSpeed;

    private String dateTime;
}
