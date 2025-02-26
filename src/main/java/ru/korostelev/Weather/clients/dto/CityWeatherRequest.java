package ru.korostelev.Weather.clients.dto;

import ru.korostelev.Weather.clients.Coordinates;


public record CityWeatherRequest(Coordinates coordinates) {
}
