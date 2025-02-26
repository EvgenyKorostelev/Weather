package ru.korostelev.Weather.clients;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.korostelev.Weather.services.UserService;

@Configuration
public class OpenWeatherMapApiConfig {

    @Bean
    public OpenWeatherMapApiClient openWeatherMapApiClient(UserService userService,
                                                           RestTemplateBuilder restTemplateBuilder) {
        return new OpenWeatherMapApiClient(userService, restTemplateBuilder.build());
    }

}
