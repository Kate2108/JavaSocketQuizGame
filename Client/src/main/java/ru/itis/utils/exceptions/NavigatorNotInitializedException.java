package ru.itis.utils.exceptions;

public class NavigatorNotInitializedException extends Exception {

    public NavigatorNotInitializedException(String message) {
        super(message);
    }

    public NavigatorNotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NavigatorNotInitializedException(Throwable cause) {
        super(cause);
    }
}
