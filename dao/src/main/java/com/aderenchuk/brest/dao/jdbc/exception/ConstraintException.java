package com.aderenchuk.brest.dao.jdbc.exception;

public class ConstraintException extends RuntimeException{
    public ConstraintException(String message) {
        super(message);
    }
}
