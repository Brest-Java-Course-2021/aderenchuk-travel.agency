package com.aderenchuk.brest.service.rest_app.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ErrorResponse {

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    private String message;
    private List<String> details;


    public ErrorResponse(String message, Exception e) {
        super();
        this.message = message;
        if(e != null) {
            this.details = Arrays.asList(e.getMessage());
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}