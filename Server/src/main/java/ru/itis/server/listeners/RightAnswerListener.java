package ru.itis.server.listeners;

import ru.itis.protocol.message.client.RightAnswerMessage;
import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class RightAnswerListener implements ClientEventListener {
    private Server server;

    private RightAnswerMessage message;
    public RightAnswerListener(RightAnswerMessage message) {
        this.message = message;
    }
    @Override
    public void initServer(Server server) {
        this.server = server;
    }

    @Override
    public void handMessage(PlayerConnection connection) {
        server.handRightAnswer(message.getSenderId(), message.getContent());
    }
}
