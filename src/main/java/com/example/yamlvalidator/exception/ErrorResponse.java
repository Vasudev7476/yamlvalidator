package com.example.yamlvalidator.exception;

public class ErrorResponse {
    private int statusCode;
    private String message;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatus() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
