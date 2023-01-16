package ru.itis.protocol.message.server;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class SystemMessage implements ContentMessage<String> {

    private final String content;
    private final int senderId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.SYSTEM_MESSAGE;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }
}
