package ru.itis.exceptions;

public class BadConnectionException extends Exception {

    public BadConnectionException() {
    }

    public BadConnectionException(String message) {
        super(message);
    }

    public BadConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
