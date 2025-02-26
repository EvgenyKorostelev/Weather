package ru.korostelev.Weather.services;

import ru.korostelev.Weather.clients.Coordinates;

public interface CoordinatesCityService {

    Coordinates getCoordinatesByName(String cityName, String userName);
}
