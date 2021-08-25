package com.neueda.blocking.chassis.exception;

import liquibase.pro.packaged.L;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FatalException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String error;
    private String description;
    private String path;

    public FatalException(){super();}

    public FatalException(String error, String description, String path){
        super(description);
    this.error = error;
    this.description = description;
    this.path = path;
    }

    public FatalException(String error, Throwable cause){
      super(error,cause);
    }


}
