package com.neueda.blocking.chassis.exception;

import com.neueda.blocking.chassis.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IdFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<ErrorResponse> handleNumberFormatError(IdFormatException ex) {
        ErrorResponse message = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getError(),ex.getDescription());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordsFetchedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ErrorResponse> handleChassisEntityNotFoundException(NoRecordsFetchedException ex) {
        ErrorResponse message = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getError(),ex.getDescription());
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ErrorResponse> handleChassisEntityNameFormatException(NameFormatException ex) {
        ErrorResponse message = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getError(),ex.getDescription());
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}