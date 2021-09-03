package com.neueda.blocking.chassis.exception;

public class InputFormatException extends ExceptionHandler {

    public InputFormatException(String message, String path) {
        super("Please enter a valid Input", message, path);
    }

    public InputFormatException(String path, NumberFormatException cause) {
        super("Please enter a valid Input", "Please check the entered Input", path, cause);
    }

}
