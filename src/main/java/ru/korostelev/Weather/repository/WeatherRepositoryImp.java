package ru.korostelev.Weather.repository;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.korostelev.Weather.entity.City;
import ru.korostelev.Weather.entity.Weather;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class WeatherRepositoryImp implements WeatherRepository {

    private List<City> cities = new ArrayList<>();

    @Override
    public void save(City city) {
        if(cities.size() < 10){
            cities.add(city);
        } else {
            cities.removeLast();
            cities.add(city);
        }
    }
}
