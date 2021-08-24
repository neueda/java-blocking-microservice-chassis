package com.neueda.blocking.chassis.exception;

public class NameFormatException extends FatalException{


    public NameFormatException(){}

    public NameFormatException(String message){ super("No records with the given name", message);}

    public NameFormatException(String message, Throwable cause){ super( message,cause);}
}
