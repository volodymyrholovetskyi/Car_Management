package com.holovetskyi.car;

import com.holovetskyi.car.type.Color;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public interface CarUtils {

    Comparator<Car> compareByModel = Comparator.comparing(car -> car.model);
    Comparator<Car> compareByColor = Comparator.comparing(car -> car.color);
    Comparator<Car> compareByPrice = Comparator.comparing(car -> car.price);
    Comparator<Car> compareByMileage = Comparator.comparing(car -> car.mileage);





    Function<Car, Color> toColor = car -> car.color;
    Function<Car, String> toModel = car -> car.model;
    Function<Car, BigDecimal> toPrice = car -> car.price;

    ToDoubleFunction<Car> toMileage = car -> car.mileage;

    Function<Car, List<String>> toComponents = car -> car.components;

    Function<Car, Stream<String>> toComponentsAsStream = car -> car.components.stream();
    static List<String> sortComponents(Car car) {
    return car.components.stream().sorted().toList();
    }


}
