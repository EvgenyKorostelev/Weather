package ru.korostelev.Weather.clients.payload;

import ru.korostelev.Weather.entity.Coordinates;


public record CityWeatherRequest(Coordinates coordinates) {
}
