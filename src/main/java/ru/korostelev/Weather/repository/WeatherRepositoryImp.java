package ru.korostelev.Weather.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.korostelev.Weather.entity.City;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Data
@Repository
public class WeatherRepositoryImp implements WeatherRepository {

    private List<City> cities = new ArrayList<>(10);

    @Override
    public void save(City city) {
        if (cities.size() != 10) {
            cities.add(city);
        } else {
            updatingByTime();
            if (cities.size() != 10) {
                cities.add(city);
            } else {
                cities.removeFirst();
                cities.add(city);
            }
        }
    }

    @Override
    public Optional<City> getCityByCityName(String cityName) {
        return cities.stream()
                .filter(o -> o.getCityName().equals(cityName))
                .findFirst();
    }

    @Override
    public List<City> getAllCities() {
        return cities;
    }

    private void updatingByTime() {
        Date currentDate = new Date();
        System.out.println(currentDate.getTime() / 1000);
        cities.removeIf(city ->
                (currentDate.getTime() / 1000 - city.getDateTime()) > 600);
    }


}
