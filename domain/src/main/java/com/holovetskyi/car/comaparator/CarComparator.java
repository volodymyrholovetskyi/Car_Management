package com.holovetskyi.car.comaparator;

import com.holovetskyi.car.Car;

import java.math.BigDecimal;
import java.util.Comparator;

public class CarComparator implements Comparator<Car> {

    public boolean compareMileage(BigDecimal actualMileage, BigDecimal otherMileage) {
        return actualMileage.compareTo(otherMileage) > 0;
    }

    @Override
    public int compare(Car o1, Car o2) {
        return 0;
    }
}
