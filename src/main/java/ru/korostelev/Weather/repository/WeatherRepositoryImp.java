package ru.korostelev.Weather.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.korostelev.Weather.entity.City;

import java.util.ArrayList;
import java.util.List;


@Data
@Repository
public class WeatherRepositoryImp implements WeatherRepository {

    private List<City> cities = new ArrayList<>();

    @Override
    public void save(City city) {
        if (cities.size() < 10) {
            cities.add(city);
        } else {
            cities.removeLast();
            cities.add(city);
        }
    }
}
