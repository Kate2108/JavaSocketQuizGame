package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class PlayerAcceptedMessage implements ContentMessage<String> {
    private final int senderId;
    private final String username;

    @Override
    public MessageTypes getType() {
        return MessageTypes.PLAYER_ACCEPTED;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }

    @Override
    public String getContent() {
        return username;
    }
}
