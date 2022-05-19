package com.holovetskyi.validator;

import java.util.Map;
import java.util.stream.Collectors;

public interface Validator<T> {

    Map<String, String> validate(T t);

    static <T> boolean validate(Validator<T> validator, T t) {
        var errors = validator.validate(t);
        System.out.println(errors
                .entrySet()
                .stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n")));
        return errors.isEmpty();
    }
}
