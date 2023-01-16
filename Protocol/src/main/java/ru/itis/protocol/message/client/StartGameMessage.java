package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.BasicMessage;

@AllArgsConstructor
public class StartGameMessage implements BasicMessage {
    private final int roomId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.GAME_START;
    }

    @Override
    public int getSenderId() {
        return roomId;
    }

}
