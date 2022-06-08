package com.holovetskyi.repo.exception;

public class CarRepoException extends RuntimeException {
    private static final String MESSAGE = "Error message: %s";

    public CarRepoException(String message) {
        super(MESSAGE.formatted(message));
    }
}
