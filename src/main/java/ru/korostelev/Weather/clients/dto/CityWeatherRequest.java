package ru.korostelev.Weather.clients.dto;

import ru.korostelev.Weather.entity.Coordinates;


public record CityWeatherRequest(Coordinates coordinates) {
}
