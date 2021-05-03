package com.aderenchuk.brest.service.rest_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TOUR_NOT_FOUND = "tour.not.found";
    public static final String VALIDATION_ERROR = "validation_error";

    @ExceptionHandler(TourNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTourNotFound(TourNotFoundException e, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(TOUR_NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception e, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorResponse(VALIDATION_ERROR, e),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
