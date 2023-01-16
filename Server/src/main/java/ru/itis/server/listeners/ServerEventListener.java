package ru.itis.server.listeners;

import ru.itis.constants.MessageTypes;
import ru.itis.protocol.message.BasicMessage;
import ru.itis.protocol.message.client.*;

public class ServerEventListener {

    public static ClientEventListener getListener(MessageTypes type, BasicMessage message){
        switch (type) {
            case RIGHT_ANSWER -> {
                return new RightAnswerListener((RightAnswerMessage) message);
            }
            case GAME_START -> {
                return new StartGameListener((StartGameMessage) message);
            }
            case PLAYER_DISCONNECT -> {
                return new PlayerDisconnectedListener();
            }
            case ROOM_CREATE -> {
                return new CreateRoomListener((CreateRoomMessage) message);
            }
            case PLAYER_INIT_USERNAME -> {
                return new InitUsernameListener((InitUsernameMessage) message);
            }
            case PLAYER_JOIN_ROOM -> {
                return new JoinRoomListener((JoinRoomMessage) message);
            }
            case PLAYER_LEAVE_ROOM -> {
                return new LeaveRoomListener();
            }
        }
        return null;
    }
}
