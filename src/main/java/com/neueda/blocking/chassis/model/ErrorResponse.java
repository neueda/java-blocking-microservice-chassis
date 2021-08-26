package com.neueda.blocking.chassis.model;

import static java.util.Objects.requireNonNull;

public record ErrorResponse(Integer statusCode, String error, String description, String path) {

    public ErrorResponse {
        requireNonNull(statusCode);
        requireNonNull(error);
        requireNonNull(description);
        requireNonNull(path);
    }

}
