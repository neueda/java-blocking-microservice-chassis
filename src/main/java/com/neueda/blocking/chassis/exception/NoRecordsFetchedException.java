package com.neueda.blocking.chassis.exception;

public class NoRecordsFetchedException extends FatalException{

    public NoRecordsFetchedException(String message,String path){ super("No records Fetched", message,path);}

    public NoRecordsFetchedException(String message, Throwable cause){ super( message,cause);}
}
