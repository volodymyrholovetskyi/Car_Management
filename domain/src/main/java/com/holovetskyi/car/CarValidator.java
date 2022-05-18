package com.holovetskyi.car;

import com.holovetskyi.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarValidator implements Validator<Car> {
    @Override
    public Map<String, String> validate(Car car) {
        var errors = new HashMap<String, String>();

        if (car == null) {
            errors.put("car object", "null");
            return errors;
        }

        var model = car.model;
        if (model == null) {
            errors.put("model", "null");
            return errors;
        } else if (!model.matches("[A-Z ]+")) {
            errors.put("model", "not correct");
            return errors;
        }

        var price = car.price;
        if (price == null) {
            errors.put("price", "null");
            return errors;
        } else if (car.price.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("price", "cannot be negative");
        }

        var mileage = car.mileage;
        if (mileage == null) {
            errors.put("mileage", "null");
            return errors;
        } else if (car.price.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("mileage", "cannot be negative");
            return errors;
        }

        var components = car.components;
        if (components == null) {
            errors.put("components", "null");
            return errors;
        }
        boolean isValidate = components.stream()
                .anyMatch(component -> component.matches("[A-Z ]+"));

        if (!isValidate) {
            errors.put("components", "not correct");
            return errors;
        }
        return errors;
    }
}

