package ru.itis.server.listeners;

import ru.itis.protocol.message.client.StartGameMessage;
import ru.itis.server.PlayerConnection;
import ru.itis.server.Server;

public class StartGameListener implements ClientEventListener{
    private Server server;

    private StartGameMessage message;

    public StartGameListener(StartGameMessage message) {
        this.message = message;
    }

    public void initServer(Server server){
        this.server = server;
    }

    @Override
    public void handMessage(PlayerConnection connection) {
        server.startGame(message.getSenderId());
    }
}
