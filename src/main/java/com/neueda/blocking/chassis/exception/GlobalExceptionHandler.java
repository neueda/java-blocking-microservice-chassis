package com.neueda.blocking.chassis.exception;

import com.neueda.blocking.chassis.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IdFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleNumberFormatError(IdFormatException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getError(),ex.getDescription(), ex.getPath());
    }

    @ExceptionHandler(NoRecordsFetchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponse handleChassisEntityNotFoundException(NoRecordsFetchedException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getError(),ex.getDescription(),ex.getPath());
    }

    @ExceptionHandler(NameFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handleChassisEntityNameFormatException(NameFormatException ex) {
      return new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getError(),ex.getDescription(),ex.getPath());

    }
}