package ru.korostelev.Weather.clients;

import ru.korostelev.Weather.entity.Coordinates;

public record CityWeatherRequest(Coordinates coordinates) {
}
