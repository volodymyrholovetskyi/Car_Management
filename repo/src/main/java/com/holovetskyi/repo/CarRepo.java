package com.holovetskyi.repo;

import com.holovetskyi.car.Car;
import com.holovetskyi.car.type.Color;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CarRepo {

    void jsonToCar(File file);

    List<Car> sortCars(String sort, Direction direction);

    List<Car> mileageWithTheHighestValue(BigDecimal mileage);

    Map<Color, List<Car>> getCarByColor(Color color);

    Map<String, List<Car>> getExpensiveCarByModel(String model);

    CarStatisticsPriceAndMileage getCarStatistics();

    List<Car> getExpensiveCar();

    List<Car> sortedComponents();

    Map<List<String>, List<Car>> getMapKeyComponent();

    List<Car> filterByPrice(BigDecimal min, BigDecimal max);
}
