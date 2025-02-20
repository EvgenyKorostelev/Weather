package ru.korostelev.Weather.clients;

import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class OpenWeatherMapApi {

    private String apiKey;

    private final RestTemplate restTemplate;

    
}
