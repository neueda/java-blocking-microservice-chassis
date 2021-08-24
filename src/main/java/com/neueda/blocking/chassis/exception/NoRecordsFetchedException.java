package com.neueda.blocking.chassis.exception;

public class NoRecordsFetchedException extends FatalException{

    public NoRecordsFetchedException(){}

    public NoRecordsFetchedException(String message){ super("No records Fetched", message);}

    public NoRecordsFetchedException(String message, Throwable cause){ super( message,cause);}
}
