package ru.korostelev.Weather.clients;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.korostelev.Weather.clients.dto.CityCoordinatesRequest;
import ru.korostelev.Weather.clients.dto.CityCoordinatesResponse;
import ru.korostelev.Weather.clients.dto.CityWeatherRequest;
import ru.korostelev.Weather.clients.dto.CityWeatherResponse;
import ru.korostelev.Weather.services.UserService;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class OpenWeatherMapApiClient {

    private final UserService userService;

    private final RestTemplate restTemplate;

    private static final ParameterizedTypeReference<List<CityCoordinatesResponse>> COORDINATES_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };


    public CityCoordinatesResponse getCoordinatesByCityName(CityCoordinatesRequest request, String userName) {

        String apiKey = userService.findUserByName(userName).getApiKey();

        String url = "http://api.openweathermap.org/geo/1.0/direct?q="
                + request.cityName() + "&appid=" + apiKey;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json");
        HttpEntity<CityCoordinatesRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<List<CityCoordinatesResponse>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, COORDINATES_TYPE_REFERENCE);


        return Objects.requireNonNull(responseEntity.getBody()).getFirst();

    }

    public CityWeatherResponse getWeatherByCityCoordinates(CityWeatherRequest request, String userName) {

        String apiKey = userService.findUserByName(userName).getApiKey();

        String url = "https://api.openweathermap.org/data/2.5/weather?lat="
                + request.coordinates().getLat() + "&lon=" + request.coordinates().getLon() + "&appid=" + apiKey
                + "&units=metric";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json");
        HttpEntity<CityWeatherRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<CityWeatherResponse> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, CityWeatherResponse.class);

        return responseEntity.getBody();
    }

}
