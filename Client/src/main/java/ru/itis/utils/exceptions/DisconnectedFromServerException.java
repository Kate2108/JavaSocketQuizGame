package ru.itis.utils.exceptions;

public class DisconnectedFromServerException extends Exception {

    public DisconnectedFromServerException(String message) {
        super(message);
    }

    public DisconnectedFromServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisconnectedFromServerException(Throwable cause) {
        super(cause);
    }
}
