package ru.itis.utils.exceptions;

public class InvalidStageStateException extends Exception {

    public InvalidStageStateException(String message) {
        super(message);
    }

    public InvalidStageStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStageStateException(Throwable cause) {
        super(cause);
    }
}
