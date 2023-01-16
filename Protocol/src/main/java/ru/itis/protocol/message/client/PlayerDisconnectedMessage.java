package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.BasicMessage;

@AllArgsConstructor
public class PlayerDisconnectedMessage implements BasicMessage {
    private final int playerId;

    @Override
    public MessageTypes getType() {
        return MessageTypes.PLAYER_DISCONNECT;
    }

    @Override
    public int getSenderId() {
        return playerId;
    }

}
