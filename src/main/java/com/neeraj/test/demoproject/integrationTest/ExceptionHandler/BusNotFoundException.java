package com.neeraj.test.demoproject.integrationTest.ExceptionHandler;

public class BusNotFoundException extends RuntimeException {
    public BusNotFoundException(String message) {
        super(message);
    }
}
