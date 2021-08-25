package com.neueda.blocking.chassis.exception;

import com.neueda.blocking.chassis.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IdFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleNumberFormatError(IdFormatException ex) {
        return logAndRespond(ex, ex.getPath());
    }

    @ExceptionHandler(ChassisEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handleChassisEntityNotFoundException(ChassisEntityNotFoundException ex) {
        return logAndRespond(ex, ex.getPath());
    }
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponse handleCustomException(CustomException ex) {
        return logAndRespond(ex, ex.getPath());
    }
    private ErrorResponse logAndRespond(Exception ex, String path) {
        String errorMsg = ex.getLocalizedMessage();
        log.error(errorMsg, ex);

        return new ErrorResponse(errorMsg, path, ex.getClass().getTypeName());
    }

}