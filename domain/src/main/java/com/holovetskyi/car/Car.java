package com.holovetskyi.car;

import com.holovetskyi.car.type.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Car {
    String model;
    BigDecimal price;
    Color color;
    double mileage;
    List<String> components;

    public boolean hasPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return this.price.compareTo(priceFrom) >= 0 && price.compareTo(priceTo) <= 0;
    }

    public boolean hasMileageGreaterThan(double mileage) {
        return this.mileage > mileage;
    }

    public boolean hasModelGivenInParameter(String model) {
        return this.model.equals(model);
    }


    public boolean containsComponent(String component) {
        return components.contains(component);
    }

    public Car withSortedComponents() {
        return Car
                .builder()
                .model(model)
                .price(price)
                .color(color)
                .mileage(mileage)
                .components(components.stream().sorted().toList())
                .build();
    }

}

