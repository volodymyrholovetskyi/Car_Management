package com.holovetskyi.json.exception;

public class JsonConverterException extends RuntimeException {

    private static final String MESSAGE = "Error message: %s";

    public JsonConverterException(String message) {
        super(MESSAGE.formatted(message));
    }
}
