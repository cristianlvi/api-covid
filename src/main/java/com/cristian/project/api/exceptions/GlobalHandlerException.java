package com.cristian.project.api.exceptions;

import com.cristian.project.api.exceptions.custom.ApiMessage;
import com.cristian.project.api.exceptions.custom.CountryException;
import com.cristian.project.api.exceptions.custom.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<?> exceptionGeneric(GenericException ge) {
        ApiMessage message = new ApiMessage(HttpStatus.BAD_REQUEST.value(),
                ge.getMessage(), LocalDate.now());

        return ResponseEntity.status(message.getErrorCode()).body(message);
    }

    @ExceptionHandler(CountryException.class)
    public ResponseEntity<ApiMessage> exceptionCountry(CountryException ce) {
        ApiMessage message = new ApiMessage(HttpStatus.BAD_REQUEST.value(),
                ce.getMessage(), LocalDate.now());


        return ResponseEntity.status(message.getErrorCode()).body(message);
    }


}
