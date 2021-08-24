package com.neueda.blocking.chassis.exception;

public class IdFormatException extends FatalException {

    public IdFormatException(){}

    public IdFormatException(String message){ super("No records Fetched", message);}

    public IdFormatException(String message, Throwable cause){ super( message,cause);}
}
