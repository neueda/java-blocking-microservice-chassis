package com.neueda.blocking.chassis.exception;

public class NameFormatException extends FatalException {
    public NameFormatException(String message, String path) {
        super("No records with the given name", message, path);
    }

    public NameFormatException(String path, IllegalArgumentException cause) {
        super("Please enter a valid name", "Please check the entered name", path, cause);
    }
}
