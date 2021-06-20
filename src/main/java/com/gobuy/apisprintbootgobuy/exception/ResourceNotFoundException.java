package com.gobuy.apisprintbootgobuy.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format("Resource %s not found for %s with value %s", resourceName, fieldName, fieldValue));
    }
}