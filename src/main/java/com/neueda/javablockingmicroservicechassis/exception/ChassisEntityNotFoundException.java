package com.neueda.javablockingmicroservicechassis.exception;

public class ChassisEntityNotFoundException extends RuntimeException {

    private String message;

    public ChassisEntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ChassisEntityNotFoundException() {
    }
}
