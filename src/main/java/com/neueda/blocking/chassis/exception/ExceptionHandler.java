package com.neueda.blocking.chassis.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionHandler extends RuntimeException {

    private static final long serialVersionUID = 1L;


    private final String error;
    private final String description;
    private final String path;

    public ExceptionHandler(String error,String description,String path, Throwable cause) {
        super(cause);
        this.path = path;
        this.error = error;
        this.description = description;
    }

}
