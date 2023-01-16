package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class CreateRoomMessage implements ContentMessage<Integer> {
    private final Integer contentMaxMembers;
    private final int creatorId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.ROOM_CREATE;
    }

    @Override
    public Integer getContent() {
        return contentMaxMembers;
    }

    @Override
    public int getSenderId() {
        return creatorId;
    }

}
