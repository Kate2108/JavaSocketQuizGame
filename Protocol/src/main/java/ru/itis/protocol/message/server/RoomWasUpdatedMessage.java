package ru.itis.protocol.message.server;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.models.Room;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class RoomWasUpdatedMessage implements ContentMessage<Room> {
    private final Room room;
    private final int senderId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.UPDATE_ROOM;
    }

    @Override
    public Room getContent() {
        return room;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }
}
