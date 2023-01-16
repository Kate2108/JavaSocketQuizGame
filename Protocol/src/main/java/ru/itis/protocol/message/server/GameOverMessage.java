package ru.itis.protocol.message.server;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.models.Room;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class GameOverMessage implements ContentMessage<Room> {
    private final Room content;
    private final int userId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.GAME_OVER;
    }

    @Override
    public Room getContent() {
        return content;
    }

    @Override
    public int getSenderId() {
        return userId;
    }

}
