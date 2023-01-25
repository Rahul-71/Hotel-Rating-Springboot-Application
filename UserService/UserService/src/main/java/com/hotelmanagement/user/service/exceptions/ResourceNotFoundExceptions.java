package com.hotelmanagement.user.service.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException {

    public ResourceNotFoundExceptions(String message) {
        super(message);
    }

    public ResourceNotFoundExceptions() {
        super("Resource not found on Server!!");
    }

}
