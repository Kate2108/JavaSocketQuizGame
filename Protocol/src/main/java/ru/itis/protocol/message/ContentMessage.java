package ru.itis.protocol.message;

public interface ContentMessage<T> extends BasicMessage {
    T getContent();
}
