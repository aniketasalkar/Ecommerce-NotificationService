package com.example.notificationservice.exceptions;

public class SendEmailFailedException extends RuntimeException {
    public SendEmailFailedException(String message) {
        super(message);
    }
}
