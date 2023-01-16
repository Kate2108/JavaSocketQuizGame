package ru.itis.protocol.message.client;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.BasicMessage;


@AllArgsConstructor
public class PlayerLeaveRoomMessage implements BasicMessage {
    private final int playerId;


    @Override
    public MessageTypes getType() {
        return MessageTypes.PLAYER_LEAVE_ROOM;
    }

    @Override
    public int getSenderId() {
        return playerId;
    }

}
