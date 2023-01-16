package ru.itis.protocol.message.server;

import lombok.AllArgsConstructor;
import ru.itis.constants.MessageTypes;
import ru.itis.models.Player;
import ru.itis.protocol.message.ContentMessage;

@AllArgsConstructor
public class PlayerAcceptedStatusMessage implements ContentMessage<Player> {
    private final int senderId;

    private Player content;
    @Override
    public MessageTypes getType() {
        return MessageTypes.PLAYER_ACCEPTED;
    }

    @Override
    public int getSenderId() {
        return senderId;
    }

    @Override
    public Player getContent() {
        return content;
    }
}
