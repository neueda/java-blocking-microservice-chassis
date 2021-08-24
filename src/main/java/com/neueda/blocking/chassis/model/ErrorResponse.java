package com.neueda.blocking.chassis.model;

import lombok.Setter;


import static java.util.Objects.requireNonNull;

public record ErrorResponse(int statusCode, String error, String description) {

    public ErrorResponse {
        requireNonNull(statusCode);
        requireNonNull(error);
        requireNonNull(description);
    }

}
