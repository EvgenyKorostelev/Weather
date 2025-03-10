package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.Coordinates;

public interface CoordinatesService {

    Coordinates findCoordinatesByName(String cityName, String userName);
}
