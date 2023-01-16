package ru.itis.protocol.message.server;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.BasicMessage;

@AllArgsConstructor
public class TimeUpMessage implements BasicMessage {
    private final int senderId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.TIME_IS_UP;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }

}
