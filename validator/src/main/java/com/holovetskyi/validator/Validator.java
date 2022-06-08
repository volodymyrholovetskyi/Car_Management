package com.holovetskyi.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.stream.Collectors;

public interface Validator<T> {
    Log LOG = LogFactory.getLog(Validator.class.getName());

    Map<String, String> validate(T t);

    static <T> boolean validate(Validator<T> validator, T t) {
        var errors = validator.validate(t);
        LOG.error(errors
                .entrySet()
                .stream()
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n")));
        return errors.isEmpty();
    }
}
