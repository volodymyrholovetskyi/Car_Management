package com.holovetskyi.repo;

import com.holovetskyi.car.Car;
import com.holovetskyi.car.type.Color;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CarRepoImpl implements CarRepo{


    @Override
    public void jsonToCar(File file) {

    }

    @Override
    public List<Car> sortCars(String sort, Direction direction) {
        return null;
    }

    @Override
    public List<Car> mileageWithTheHighestValue(BigDecimal mileage) {
        return null;
    }

    @Override
    public Map<Color, List<Car>> getCarByColor(Color color) {
        return null;
    }

    @Override
    public Map<String, List<Car>> getExpensiveCarByModel(String model) {
        return null;
    }

    @Override
    public CarStatisticsPriceAndMileage getCarStatistics() {
        return null;
    }

    @Override
    public List<Car> getExpensiveCar() {
        return null;
    }

    @Override
    public List<Car> sortedComponents() {
        return null;
    }

    @Override
    public Map<List<String>, List<Car>> getMapKeyComponent() {
        return null;
    }

    @Override
    public List<Car> filterByPrice(BigDecimal min, BigDecimal max) {
        return null;
    }
}
