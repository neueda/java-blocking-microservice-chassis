package com.neueda.blocking.chassis.exception;

public class IdFormatException extends FatalException {


    public IdFormatException(String message,String path){ super("No records Fetched", message,path);}

    public IdFormatException(String message, Throwable cause){ super( message,cause);}
}
