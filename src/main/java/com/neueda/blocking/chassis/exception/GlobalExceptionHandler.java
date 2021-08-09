package com.neueda.blocking.chassis.exception;

import com.neueda.blocking.chassis.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ChassisEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handleChassisEntityNotFoundException(ChassisEntityNotFoundException ex) {
        return logAndRespond(ex, ex.getPath());
    }
    private ErrorResponse logAndRespond(Exception ex, String path) {
        String errorMsg = ex.getLocalizedMessage();
        log.error(errorMsg, ex);

        return new ErrorResponse(errorMsg, path, ex.getClass().getTypeName());
    }

}
