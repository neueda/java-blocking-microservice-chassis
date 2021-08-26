package com.neueda.blocking.chassis.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class FatalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @NonNull
    private final String error;
    @NonNull
    private final String description;
    @NonNull
    private final String path;

    public FatalException(String error, String description, String path, Throwable cause) {
        super(cause);
        this.path = path;
        this.error = error;
        this.description = description;
    }

}
