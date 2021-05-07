package com.aderenchuk.brest.dao.jdbc.exception;

public class SuchBlogNotExistsException extends RuntimeException{
        public SuchBlogNotExistsException(String message) {
            super(message);
        }
}
