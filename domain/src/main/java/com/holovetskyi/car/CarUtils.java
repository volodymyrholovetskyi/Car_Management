package com.holovetskyi.car;

import java.util.Comparator;

public interface CarUtils {

    Comparator<Car> compareByModel = Comparator.comparing(car -> car.model);
    Comparator<Car> compareByColor = Comparator.comparing(car -> car.color);
    Comparator<Car> compareByPrice = Comparator.comparing(car -> car.price);
    Comparator<Car> compareByMileage = Comparator.comparing(car -> car.mileage);
}
