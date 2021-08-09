package com.neueda.blocking.chassis.model;

import static java.util.Objects.requireNonNull;

public record ErrorResponse(String error, String path, String exception) {

    public ErrorResponse {
        requireNonNull(error);
        requireNonNull(path);
        requireNonNull(exception);
    }
}
