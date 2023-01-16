package ru.itis.protocol.message;

import ru.itis.constants.MessageTypes;

import java.io.Serializable;

public interface BasicMessage extends Serializable {
    MessageTypes getType();

    int getSenderId();
}
