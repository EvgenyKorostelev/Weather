package ru.korostelev.Weather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates{

    private Double lat;

    private Double lon;
}
