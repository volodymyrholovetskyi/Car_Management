package com.holovetskyi.car;

import com.holovetskyi.car.type.Color;
import com.holovetskyi.validator.Validator;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Validator<Car> validator = new CarValidator();

        Car car = Car.builder()
                .model("CAR")
                .price(new BigDecimal("2"))
                .color(Color.BLACK)
                .mileage(new BigDecimal("1120"))
                .components(List.of("ONE", "TWO"))
                .build();

        boolean validate = Validator.validate(validator, car);

        System.out.println(validate);

        System.out.println(car);
    }
}

