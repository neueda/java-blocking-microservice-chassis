package com.neueda.blocking.chassis.exception;

public class CustomException extends FatalException {

    public CustomException(String message, String path) {

        super("No records with the given name", message, path);

    }

    public CustomException(String path, IllegalArgumentException cause) {

        super("Please enter a valid name", "Please check the entered name", path, cause);

    }
}