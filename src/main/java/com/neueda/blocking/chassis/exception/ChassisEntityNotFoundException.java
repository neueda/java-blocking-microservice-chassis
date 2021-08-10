package com.neueda.blocking.chassis.exception;

public class ChassisEntityNotFoundException extends RuntimeException {

    private final String path;

    public ChassisEntityNotFoundException(String path, String message) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}