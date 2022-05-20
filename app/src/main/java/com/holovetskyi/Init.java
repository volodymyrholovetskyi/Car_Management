package com.holovetskyi;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Init {

    public static void init(File file) {
        toJson(file, build());
    }

    private static void toJson(File file, CarsImpl carsDao) {
        try {
            new ObjectMapper().writeValue(file, carsDao);
        } catch (IOException e) {
            log.warn("File not found: " + e);
        }
    }

    private static CarsImpl build(){
        Car bmw = new Car.CarBuilder()
                .model("BMW")
                .price(new BigDecimal("1600"))
                .mileage(new BigDecimal("250"))
                .color(Color.BLACK)
                .components(List.of("DPS", "ALLOY WHEELS"))
                .build();

        Car audi = new Car.CarBuilder()
                .model("BMW")
                .price(new BigDecimal("1600"))
                .mileage(new BigDecimal("300"))
                .color(Color.BLACK)
                .components(List.of("DPS", "AIR CONDITIONING"))
                .build();

        return new CarsImpl(List.of(bmw, audi));
    }
}
