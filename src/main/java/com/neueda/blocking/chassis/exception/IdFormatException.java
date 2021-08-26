package com.neueda.blocking.chassis.exception;

public class IdFormatException extends FatalException {


    public IdFormatException(String message, String path) {
        super("Please enter a valid Id", message, path);
    }

    public IdFormatException(String path, NumberFormatException cause) {
        super("Please enter a valid Id", "Please check the entered Id", path, cause);
    }
}
