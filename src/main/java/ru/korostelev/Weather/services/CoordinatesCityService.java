package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.Coordinates;

public interface CoordinatesCityService {

    Coordinates getCoordinatesByName(String cityName);
}
