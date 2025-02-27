package ru.korostelev.Weather.clients;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.korostelev.Weather.services.UserService;

@Configuration
public class ClientBeans {

    @Bean
    public OpenWeatherRestClientImp openWeatherRestClientImp(UserService userService,
                                                             RestTemplateBuilder restTemplateBuilder) {
        return new OpenWeatherRestClientImp(userService, restTemplateBuilder.build());
    }

}
