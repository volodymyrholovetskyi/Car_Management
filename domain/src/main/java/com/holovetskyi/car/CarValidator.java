package com.holovetskyi.car;

import com.holovetskyi.validator.Validator;

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
        } else if (!model.matches("[A-Z]+")) {
            errors.put("model", "not correct");
            return errors;
        }


    }
}
}
