package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class JoinRoomMessage implements ContentMessage<Integer> {
    private final int senderId;
    private final Integer roomId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.PLAYER_JOIN_ROOM;
    }

    @Override
    public Integer getContent() {
        return roomId;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }
}
