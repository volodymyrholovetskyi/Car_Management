package com.holovetskyi.car.comaparator;

import com.holovetskyi.car.Car;

import java.util.Comparator;

public interface CustomComparator extends Comparator<Car> {


    @Override
    default int compare(Car o1, Car o2) {
        return 0;
    }
}
