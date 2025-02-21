package ru.korostelev.Weather.clients;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.korostelev.Weather.services.UserService;

@AllArgsConstructor
public class OpenWeatherMapApiClient {

    private final UserService userService;

    private final RestTemplate restTemplate;

    public CityCoordinatesResponse getCoordinatesByCityName(CityCoordinatesRequest request, String userName) {

        String apiKey = userService.findUserByName(userName).getApiKey();

        String url = "http://api.openweathermap.org/geo/1.0/direct?q="
                + request.cityName() + "&appid=" + apiKey;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json");
        HttpEntity<CityCoordinatesRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<CityCoordinatesResponse> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, CityCoordinatesResponse.class);

        return responseEntity.getBody();
    }

    public CityWeatherResponse getWeatherByCityCoordinates(CityWeatherRequest request, String userName) {

        String apiKey = userService.findUserByName(userName).getApiKey();

        String url = "https://api.openweathermap.org/data/3.0/onecall?lat="
                + request.coordinates().getLat() + "&lon=" + request.coordinates().getLon() + "&appid=" + apiKey;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json");
        HttpEntity<CityWeatherRequest> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<CityWeatherResponse> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, httpEntity, CityWeatherResponse.class);

        return responseEntity.getBody();
    }

}
