package ru.korostelev.Weather.services;

import ru.korostelev.Weather.entity.Coordinates;

public interface CoordinatesCityService {

    Coordinates findCoordinatesByName(String cityName, String userName);
}
