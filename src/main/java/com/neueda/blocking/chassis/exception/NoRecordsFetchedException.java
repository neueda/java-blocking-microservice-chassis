package com.neueda.blocking.chassis.exception;

public class NoRecordsFetchedException extends FatalException {

    public NoRecordsFetchedException(String message, String path) {
        super("No records Fetched", message, path);
    }

    public NoRecordsFetchedException(String path, Throwable cause) {
        super("Please enter a valid name", "Please check the entered name", path, cause);
    }

}
