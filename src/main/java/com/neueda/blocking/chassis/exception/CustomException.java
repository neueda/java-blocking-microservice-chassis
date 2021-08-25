package com.neueda.blocking.chassis.exception;

public class CustomException extends Exception {

    private final String path;

    public CustomException(String message, Exception e, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
